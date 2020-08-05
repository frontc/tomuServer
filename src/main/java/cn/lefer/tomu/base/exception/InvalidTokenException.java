package cn.lefer.tomu.base.exception;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class InvalidTokenException extends BaseException {
    public InvalidTokenException() {
        super();
        this.setErrorResponse(
                ErrorResponse.builder().code(SystemErrorCode.INVALID_TOKEN.getCode())
                        .status(SystemErrorCode.INVALID_TOKEN.getStatus())
                        .message(SystemErrorCode.INVALID_TOKEN.name())
                        .build()
        );
    }
}
