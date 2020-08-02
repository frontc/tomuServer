package cn.lefer.tomu.channel.representation;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.ToString;

import java.util.Date;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 歌单项目信息
 */
@ToString
public class PlaylistItemRepresentation {
    int channelID;
    int songID;
    String songUrl;
    String songName;
    String artistName;
    String coverUrl;
    String lrcUrl;
    String mp3Url;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date createDate;
}
