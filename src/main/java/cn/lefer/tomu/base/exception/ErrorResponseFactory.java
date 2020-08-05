package cn.lefer.tomu.base.exception;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/5
 * @Description :
 */
public class ErrorResponseFactory {
    public static ErrorResponse generate(SystemErrorCode errorCode){
        return ErrorResponse.builder().code(errorCode.getCode())
                .message(errorCode.name())
                .status(errorCode.getStatus())
                .build();
    }
}
