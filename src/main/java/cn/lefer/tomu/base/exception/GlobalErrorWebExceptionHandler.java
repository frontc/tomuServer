package cn.lefer.tomu.base.exception;

import org.springframework.boot.autoconfigure.web.ResourceProperties;
import org.springframework.boot.autoconfigure.web.reactive.error.AbstractErrorWebExceptionHandler;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.reactive.error.ErrorAttributes;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import javax.validation.ConstraintViolationException;
import java.util.Map;
import java.util.Optional;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 全局异常处理类
 */
@Component
@Order(-2)
public class GlobalErrorWebExceptionHandler extends AbstractErrorWebExceptionHandler {

    public GlobalErrorWebExceptionHandler(ErrorAttributes errorAttributes,
                                          ResourceProperties resourceProperties,
                                          ServerCodecConfigurer serverCodecConfigurer,
                                          ApplicationContext applicationContext) {
        super(errorAttributes, resourceProperties, applicationContext);
        this.setMessageWriters(serverCodecConfigurer.getWriters());
    }

    @Override
    protected RouterFunction<ServerResponse> getRoutingFunction(ErrorAttributes errorAttributes) {
        return RouterFunctions.route(
                RequestPredicates.all(), this::renderErrorResponse);
    }

    private Mono<ServerResponse> renderErrorResponse(
            ServerRequest request) {
        ErrorResponse errorResponse = this.getErrorResponse(request);
        return ServerResponse.status(errorResponse.getStatus())
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(errorResponse));
    }

    private ErrorResponse getErrorResponse(ServerRequest request) {
        Map<String, Object> errorPropertiesMap = getErrorAttributes(request,
                ErrorAttributeOptions.of(ErrorAttributeOptions.Include.BINDING_ERRORS,
                        ErrorAttributeOptions.Include.EXCEPTION,
                        ErrorAttributeOptions.Include.STACK_TRACE,
                        ErrorAttributeOptions.Include.MESSAGE));
        Throwable throwable = this.getError(request);
        String message = Optional.ofNullable(errorPropertiesMap.get("message")).map(Object::toString).orElse("");
        if (throwable instanceof BaseException) return ((BaseException) throwable).getErrorResponse();
        int status = (int) errorPropertiesMap.get("status");
        if (status == 404) return ErrorResponseFactory.generate(SystemErrorCode.URL_NOT_EXIST);
        if (throwable instanceof NumberFormatException)
            return ErrorResponseFactory.generate(SystemErrorCode.PATH_VARIABLE_TYPE_MISMATCH);
        if (throwable instanceof WebExchangeBindException) {
            ErrorResponse errorResponse = ErrorResponseFactory.generate(SystemErrorCode.INVALID_PARAMETER);
            if (message.contains("default message")) {
                String[] stringList = message.split("default message");
                String simpleMsg = stringList[stringList.length - 1].replace("[", "")
                        .replace("]", "")
                        .replace("\"", "").trim();
                errorResponse.setMessage(simpleMsg);
            }
            return errorResponse;
        }
        if (throwable instanceof ConstraintViolationException) {
            ErrorResponse errorResponse = ErrorResponseFactory.generate(SystemErrorCode.INVALID_PARAMETER);
            String[] stringList = message.split(":");
            String simpleMsg = stringList[stringList.length - 1].trim();
            errorResponse.setMessage(simpleMsg);
            return errorResponse;
        }
        return ErrorResponse.builder().code("-1").message(message).status(status).build();
    }
}
