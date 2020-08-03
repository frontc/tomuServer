package cn.lefer.tomu.channel.exception;

import cn.lefer.tomu.base.exception.BaseException;
import cn.lefer.tomu.base.exception.ErrorResponse;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class PlaylistItemAlreadyExistException extends BaseException {
    public PlaylistItemAlreadyExistException() {
        super();
        this.setErrorResponse(
                ErrorResponse.builder().code(ChannelErrorCode.PLAYLIST_ITEM_ALREADY_EXIST.getCode())
                        .status(ChannelErrorCode.PLAYLIST_ITEM_ALREADY_EXIST.getStatus())
                        .message(ChannelErrorCode.PLAYLIST_ITEM_ALREADY_EXIST.name())
                        .build()
        );
    }
}
