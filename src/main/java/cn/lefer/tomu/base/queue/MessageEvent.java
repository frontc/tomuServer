package cn.lefer.tomu.base.queue;

import lombok.Data;
import lombok.ToString;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/7/4
 * @Description : 消息实体
 */
@Data
@ToString
public class MessageEvent {
    MessageType type;
    Object value;
}
