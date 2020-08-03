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
public interface ChannelMapper {
    void insert(Channel channel);
    Channel byID(@Param("channelID") int channelID);

    void insertPlaylistItem(PlaylistItem playlistItem);

    PlaylistItem selectNormalPlaylistItemByChannelIDAndSongID(@Param("channelID") int channelID,
                                                              @Param("songID") int songID,
                                                              @Param("playlistItemStatus") PlaylistItemStatus playlistItemStatus);
}
