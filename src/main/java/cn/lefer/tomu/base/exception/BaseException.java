package cn.lefer.tomu.base.exception;

import cn.lefer.tomu.base.exception.ErrorResponse;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 基础异常类
 */
@Getter
@Setter
public abstract class BaseException extends RuntimeException{
    ErrorResponse errorResponse;
    public BaseException(){
        super();
    }
}
