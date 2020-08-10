package cn.lefer.tomu.base.exception;

import cn.lefer.tomu.channel.exception.ChannelErrorCode;
import cn.lefer.tomu.song.exception.SongErrorCode;
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
public abstract class BaseException extends RuntimeException {
    final ErrorResponse errorResponse;

    public BaseException(ErrorCode errorCode) {
        super();
        this.errorResponse =
                ErrorResponse.builder().code(errorCode.getCode())
                        .status(errorCode.getStatus())
                        .message(errorCode.getName())
                        .build();
    }
}
