package cn.lefer.tomu.channel.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 频道信息
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChannelRepresentation {
    int channelID;
    String channelName;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date channelCreateDate;
    List<PlaylistItemRepresentation> playlist = new ArrayList<>();
    int lastSongID;
    double lastPosition;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date lastPlayDate;
}
