package cn.lefer.tomu.channel.exception;

import cn.lefer.tomu.base.exception.BaseException;
import cn.lefer.tomu.base.exception.ErrorResponse;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class ChannelNotExistException extends BaseException {
    public ChannelNotExistException() {
        super();
        this.setErrorResponse(
                ErrorResponse.builder().code(ChannelErrorCode.CHANNEL_NOT_EXIST.getCode())
                        .status(ChannelErrorCode.CHANNEL_NOT_EXIST.getStatus())
                        .message(ChannelErrorCode.CHANNEL_NOT_EXIST.name())
                        .build()
        );
    }
}
