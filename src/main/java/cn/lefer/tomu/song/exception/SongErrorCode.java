package cn.lefer.tomu.song.exception;

import cn.lefer.tomu.base.exception.ErrorCode;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道类异常定义
 */

public enum SongErrorCode implements ErrorCode {
    SONG_VERIFY_FAILED("4061",406),
    SONG_CANNOT_REACHABLE("4060",406);

    private final String code;
    private final int status;


    SongErrorCode(String code, int status) {
        this.code = code;
        this.status = status;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public int getStatus() {
        return status;
    }

    @Override
    public String getName() {
        return this.name();
    }

    public static int getStatusByCode(String code) {
        for (SongErrorCode enums : SongErrorCode.values()) {
            if (enums.getCode().equals(code)) {
                return enums.getStatus();
            }
        }
        return 400;
    }
}
