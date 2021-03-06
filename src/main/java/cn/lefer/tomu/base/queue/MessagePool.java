package cn.lefer.tomu.base.queue;

import com.lmax.disruptor.BlockingWaitStrategy;
import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;

/**
 * 使用单例模式实现的消息池，对外提供消息提供者实例
 *
 * @author fangchao
 * @since 2018-07-27 10:26
 **/
@Component
public class MessagePool {
    private final MessageProducer messageProducer;
    @Value("${tomu.queue.buffer.size}")
    private final int BUFFER_SIZE = 2048;

    public MessagePool() {
        Log logger = LogFactory.getLog(this.getClass());
        //需要创建多少个生产者
        logger.info("开始初始化生产者...");
        MessageFactory factory = new MessageFactory();
        // 指明RingBuffer的大小，必须为2的幂
        Disruptor<MessageEvent> disruptor =
                new Disruptor<>(factory,
                        BUFFER_SIZE, Executors.defaultThreadFactory(),
                        ProducerType.SINGLE,
                        new BlockingWaitStrategy());
        // 置入处理逻辑
        disruptor.handleEventsWith(new MessageConsumer());
        RingBuffer<MessageEvent> ringBuffer = disruptor.start();
        this.messageProducer = new MessageProducer(ringBuffer);
        logger.info("生产者初始化完成!");
    }

    public MessageProducer getMessageProducer() {
        return messageProducer;
    }
}
