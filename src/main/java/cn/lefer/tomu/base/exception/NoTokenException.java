package cn.lefer.tomu.base.exception;

import static cn.lefer.tomu.base.exception.SystemErrorCode.NO_TOKEN;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class NoTokenException extends BaseException {
    public NoTokenException() {
        super(NO_TOKEN);
    }
}
