package cn.lefer.tomu.song.exception;

import cn.lefer.tomu.base.exception.BaseException;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class SongCannotReachableException extends BaseException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public SongCannotReachableException() {
        super(SongErrorCode.SONG_CANNOT_REACHABLE);
    }
}
