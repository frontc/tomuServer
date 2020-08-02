package cn.lefer.tomu.channel.model;

import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 歌单
 */
@Getter
@Setter
public class Playlist {
    private long playlistID;
    int channelID;
    int songID;
    Date addDate;
    PlaylistItemStatus playlistItemStatus;
}
