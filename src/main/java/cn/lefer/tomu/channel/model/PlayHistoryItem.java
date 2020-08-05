package cn.lefer.tomu.channel.model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PlayHistoryItem {
    private long playHistoryItemID;
    private int channelID;
    private int songID;
    double lastPosition;
    private Date playDate;
}
