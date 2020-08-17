package cn.lefer.tomu.base.exception;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道不存在
 */
public class ChannelFullException extends BaseException {
    /**
     *
     */
    private static final long serialVersionUID = 6388655582977422142L;

    public ChannelFullException() {
        super(SystemErrorCode.CHANNEL_IS_FULL);
    }
}
