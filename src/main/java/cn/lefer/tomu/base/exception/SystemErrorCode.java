package cn.lefer.tomu.base.exception;

import org.springframework.http.HttpStatus;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道类异常定义
 */

public enum SystemErrorCode implements ErrorCode{
    PATH_VARIABLE_TYPE_MISMATCH("4221",422),
    INVALID_PARAMETER("4220",422),
    URL_NOT_EXIST("4040",404),
    NO_TOKEN("4030", 403),
    INVALID_TOKEN("4031", 403),
    CHANNEL_IS_FULL("4000",400);

    private final String code;
    private final int status;


    SystemErrorCode(String code, int status) {
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
        for (SystemErrorCode enums : SystemErrorCode.values()) {
            if (enums.getCode().equals(code)) {
                return enums.getStatus();
            }
        }
        return HttpStatus.BAD_REQUEST.value();
    }
}
