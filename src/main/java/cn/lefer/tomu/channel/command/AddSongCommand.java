package cn.lefer.tomu.channel.command;

import cn.lefer.tomu.base.constant.SongSource;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

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
    @NotBlank(message = "mp3Url is required!")
    String mp3Url;
    String coverUrl;
    String lrcUrl;
    @NotBlank(message = "artistName is required!")
    String artistName;
    @NotBlank(message = "songName is required!")
    String songName;
}
