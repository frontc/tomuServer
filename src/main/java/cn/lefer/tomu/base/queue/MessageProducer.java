package cn.lefer.tomu.base.queue;

import com.lmax.disruptor.RingBuffer;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/7/4
 * @Description : 消息生产者
 */
@Slf4j
public class MessageProducer {
    private final RingBuffer<MessageEvent> ringBuffer;

    public MessageProducer(RingBuffer<MessageEvent> messageEventRingBuffer) {
        this.ringBuffer = messageEventRingBuffer;
    }

    public void onData(MessageType type, Object object) {
        long sequence = ringBuffer.next();
        try {
            MessageEvent msg = ringBuffer.get(sequence);
            msg.setType(type);
            msg.setValue(object);
            log.debug("发送消息：" + msg.toString());
        } finally {
            ringBuffer.publish(sequence);
        }
    }
}
