package cn.lefer.tomu.base.queue;

import cn.lefer.tomu.base.utils.SpringUtils;
import cn.lefer.tomu.channel.model.PlayHistoryItem;
import cn.lefer.tomu.channel.model.PlayHistoryItemRepository;
import com.lmax.disruptor.EventHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/7/4
 * @Description : 消息消费者
 */
@Slf4j
public class MessageConsumer implements EventHandler<MessageEvent> {
    @Override
    public void onEvent(MessageEvent messageEvent, long l, boolean b) {
        if (MessageType.NEW_PLAY_HISTORY.equals(messageEvent.getType())) {
            PlayHistoryItemRepository repository = SpringUtils.getBean(PlayHistoryItemRepository.class);
            PlayHistoryItem item = (PlayHistoryItem) messageEvent.getValue();
            PlayHistoryItem lastPlayHistoryItem = repository.selectLastItemByChannelID(item.getChannelID());
            if(lastPlayHistoryItem!=null && lastPlayHistoryItem.getSongID()==item.getSongID()){
                item.setPlayHistoryItemID(lastPlayHistoryItem.getPlayHistoryItemID());
            }
            repository.save(item);
            log.debug("消息消费成功：" + messageEvent.getType()+"-"+messageEvent.getValue());
        } else {
            log.error("无法识别的消息类型，消息处理失败:" + messageEvent.getType() + "-" + messageEvent.getValue());
        }
    }
}
