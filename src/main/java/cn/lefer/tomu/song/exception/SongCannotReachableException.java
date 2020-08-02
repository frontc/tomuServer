package cn.lefer.tomu.song.exception;

import cn.lefer.tomu.base.exception.BaseException;
import cn.lefer.tomu.base.exception.ErrorResponse;
import cn.lefer.tomu.channel.exception.ChannelErrorCode;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class SongCannotReachableException extends BaseException {
    public SongCannotReachableException() {
        super();
        this.setErrorResponse(
                ErrorResponse.builder().code(ChannelErrorCode.SONG_CANNOT_REACHABLE.getCode())
                        .status(ChannelErrorCode.SONG_CANNOT_REACHABLE.getStatus())
                        .message(ChannelErrorCode.SONG_CANNOT_REACHABLE.name())
                        .build()
        );
    }
}
