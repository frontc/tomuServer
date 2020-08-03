package cn.lefer.tomu.channel.model;

import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import lombok.*;

import java.util.Date;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : 歌单
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PlaylistItem {
    private long playlistItemID;
    int channelID;
    int songID;
    Date addDate;
    PlaylistItemStatus playlistItemStatus;
}
