package cn.lefer.tomu.channel.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PlayHistoryItemMapper {

    PlayHistoryItem selectLastItemByChannelID(@Param("channelID") int channelID);

    void update(PlayHistoryItem playHistoryItem);

    void insert(PlayHistoryItem playHistoryItem);
}
