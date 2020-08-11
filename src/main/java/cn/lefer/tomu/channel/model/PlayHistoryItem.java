package cn.lefer.tomu.channel.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayHistoryItem {
    private long playHistoryItemID;
    private int channelID;
    private int songID;
    double lastPosition;
    private Date playDate;
}
