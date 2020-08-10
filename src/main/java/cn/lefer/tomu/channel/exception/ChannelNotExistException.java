package cn.lefer.tomu.channel.exception;

import cn.lefer.tomu.base.exception.BaseException;

import static cn.lefer.tomu.channel.exception.ChannelErrorCode.CHANNEL_NOT_EXIST;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class ChannelNotExistException extends BaseException {
    public ChannelNotExistException() {
        super(CHANNEL_NOT_EXIST);
    }
}
