package cn.lefer.tomu.base.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/22
 * @Description : 错误响应封装类
 */
@Getter
@Setter
@Builder
public class ErrorResponse implements Serializable {
    private static final long serialVersionUID = 1L;
    int status;
    String code;
    String message;
}
