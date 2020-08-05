package cn.lefer.tomu.base.exception;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class ChannelFullException extends BaseException {
    public ChannelFullException() {
        super();
        this.setErrorResponse(
                ErrorResponse.builder().code(SystemErrorCode.CHANNEL_IS_FULL.getCode())
                        .status(SystemErrorCode.CHANNEL_IS_FULL.getStatus())
                        .message(SystemErrorCode.CHANNEL_IS_FULL.name())
                        .build()
        );
    }
}
