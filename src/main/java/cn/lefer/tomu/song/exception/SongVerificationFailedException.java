package cn.lefer.tomu.song.exception;

import cn.lefer.tomu.base.exception.BaseException;

import static cn.lefer.tomu.song.exception.SongErrorCode.SONG_VERIFY_FAILED;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class SongVerificationFailedException extends BaseException {
    public SongVerificationFailedException() {
        super(SONG_VERIFY_FAILED);

    }
}
