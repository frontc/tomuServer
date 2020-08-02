package cn.lefer.tomu.channel.command;

import cn.lefer.tomu.base.constant.SongSource;
import lombok.Getter;
import lombok.Setter;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 用于接受新增歌曲的相关参数
 */
@Getter
@Setter
public class AddSongCommand {
    SongSource songSource;
    String songUrl;
    String mp3Url;
    String coverUrl;
    String lrcUrl;
    String artistName;
    String songName;
}
