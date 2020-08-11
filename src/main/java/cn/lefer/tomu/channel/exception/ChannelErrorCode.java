package cn.lefer.tomu.channel.exception;

import cn.lefer.tomu.base.exception.ErrorCode;
import org.springframework.http.HttpStatus;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道类异常定义
 */

public enum ChannelErrorCode implements ErrorCode {
    CHANNEL_NOT_EXIST("4001", 400),
    PLAYLIST_ITEM_ALREADY_EXIST("4090",409);


    private final String code;
    private final int status;


    ChannelErrorCode(String code, int status) {
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
        for (ChannelErrorCode enums : ChannelErrorCode.values()) {
            if (enums.getCode().equals(code)) {
                return enums.getStatus();
            }
        }
        return HttpStatus.BAD_REQUEST.value();
    }
}
