package cn.lefer.tomu.base.exception;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道类异常定义
 */

public enum SystemErrorCode {
    NO_TOKEN("4030", 403),
    INVALID_TOKEN("4031", 403),
    CHANNEL_IS_FULL("4000",400);

    private final String code;
    private final int status;


    SystemErrorCode(String code, int status) {
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
        for (SystemErrorCode enums : SystemErrorCode.values()) {
            if (enums.getCode().equals(code)) {
                return enums.getStatus();
            }
        }
        return 400;
    }
}
