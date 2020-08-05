package cn.lefer.tomu.base.exception;

import cn.lefer.tomu.channel.exception.ChannelErrorCode;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class NoTokenException extends BaseException {
    public NoTokenException() {
        super();
        this.setErrorResponse(
                ErrorResponse.builder().code(SystemErrorCode.NO_TOKEN.getCode())
                        .status(SystemErrorCode.NO_TOKEN.getStatus())
                        .message(SystemErrorCode.NO_TOKEN.name())
                        .build()
        );
    }
}
