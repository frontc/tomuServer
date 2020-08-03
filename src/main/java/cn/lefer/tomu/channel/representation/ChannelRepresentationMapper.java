package cn.lefer.tomu.channel.representation;

import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import cn.lefer.tomu.base.constant.SongStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/24
 * @Description : channel mapper
 */
@Mapper
public interface ChannelRepresentationMapper {

    ChannelRepresentation queryChannelByChannelID(@Param("channelID") int channelID);

    List<PlaylistItemRepresentation> queryNormalPlaylistByChannelID(@Param("channelID") int channelID,
                                                              @Param("playlistItemStatus") PlaylistItemStatus playlistItemStatus,
                                                              @Param("songStatus") SongStatus songStatus);

    PlaylistItemRepresentation queryPlaylistItemByID(long playlistItemID);
}
