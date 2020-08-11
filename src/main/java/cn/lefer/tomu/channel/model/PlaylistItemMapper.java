package cn.lefer.tomu.channel.model;

import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/24
 * @Description : channel mapper
 */
@Mapper
public interface PlaylistItemMapper {
    void insert(PlaylistItem playlistItem);

    PlaylistItem selectNormalPlaylistItemByChannelIDAndSongID(@Param("channelID") int channelID,
                                                              @Param("songID") int songID,
                                                              @Param("playlistItemStatus") PlaylistItemStatus playlistItemStatus);

    PlaylistItem byChannelIDAndSongID(@Param("channelID") int channelID,
                                      @Param("songID") int songID,
                                      @Param("playlistItemStatus") PlaylistItemStatus playlistItemStatus);

    void updateStatusByID(@Param("playlistItemID") long playlistItemID, @Param("playlistItemStatus") PlaylistItemStatus playlistItemStatus);
}
