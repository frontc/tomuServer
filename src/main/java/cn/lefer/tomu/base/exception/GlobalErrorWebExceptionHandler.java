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
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.Map;

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
        if (throwable instanceof BaseException) {
            return ((BaseException) throwable).getErrorResponse();
        } else {
            return ErrorResponse.builder().code("-1")
                    .message(errorPropertiesMap.get("message").toString())
                    .status((int) errorPropertiesMap.get("status")).build();
        }
    }
}
