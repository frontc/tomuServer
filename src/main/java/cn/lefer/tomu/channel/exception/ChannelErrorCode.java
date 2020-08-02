package cn.lefer.tomu.channel.exception;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道类异常定义
 */

public enum ChannelErrorCode {
    CHANNEL_NOT_EXIST("4001", 400),
    SONG_VERIFY_FAILED("4061",406),
    SONG_CANNOT_REACHABLE("4060",406);

    private final String code;
    private final int status;


    ChannelErrorCode(String code, int status) {
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public int getStatus() {
        return status;
    }

    public static int getStatusByCode(String code) {
        for (ChannelErrorCode enums : ChannelErrorCode.values()) {
            if (enums.getCode().equals(code)) {
                return enums.getStatus();
            }
        }
        return 400;
    }
}
