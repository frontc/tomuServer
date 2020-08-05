package cn.lefer.tomu.channel.model;

import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class PlayHistoryItemRepository {
    @Resource
    PlayHistoryItemMapper playHistoryItemMapper;

    public PlayHistoryItem selectLastItemByChannelID(int channelID) {
        return playHistoryItemMapper.selectLastItemByChannelID(channelID);
    }

    public void save(PlayHistoryItem playHistoryItem) {
        if(playHistoryItem.getPlayHistoryItemID()>0){
            playHistoryItemMapper.update(playHistoryItem);
        }else{
            playHistoryItemMapper.insert(playHistoryItem);
        }
    }
}
