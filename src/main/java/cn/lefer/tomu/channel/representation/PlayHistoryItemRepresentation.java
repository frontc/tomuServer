package cn.lefer.tomu.channel.representation;

import cn.lefer.tomu.base.constant.SongStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class PlayHistoryItemRepresentation {
    private long playHistoryItemID;
    private int channelID;
    private int songID;
    private String songName;
    private SongStatus songStatus;
    private String artistName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    private Date playDate;
    private String graceDateClassification;
}
