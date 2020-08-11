package cn.lefer.tomu.channel.representation;

import cn.lefer.tomu.base.constant.SongSource;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 歌单项目信息
 */
@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistItemRepresentation {
    long playlistItemID;
    int channelID;
    int songID;
    SongSource songSource;
    String songUrl;
    String songName;
    String artistName;
    String coverUrl;
    String lrcUrl;
    String mp3Url;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date createDate;
}
