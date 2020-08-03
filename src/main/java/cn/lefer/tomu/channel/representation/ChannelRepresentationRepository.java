package cn.lefer.tomu.channel.representation;


import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import cn.lefer.tomu.base.constant.SongStatus;
import cn.lefer.tomu.channel.exception.ChannelNotExistException;
import cn.lefer.tomu.channel.model.Channel;
import cn.lefer.tomu.channel.model.ChannelMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ChannelRepresentationRepository {
    @Resource
    private ChannelRepresentationMapper channelRepresentationMapper;

    public ChannelRepresentation get(int channelID) {
        try{
            ChannelRepresentation channelRepresentation = channelRepresentationMapper.queryChannelByChannelID(channelID);
            channelRepresentation.setPlaylist(channelRepresentationMapper.queryNormalPlaylistByChannelID(channelID, PlaylistItemStatus.NORMAL, SongStatus.NORMAL));
            return channelRepresentation;
        }catch (Exception ex){
            throw new ChannelNotExistException();
        }
    }

    public PlaylistItemRepresentation getPlaylistItemByID(long playlistItemID) {
        return channelRepresentationMapper.queryPlaylistItemByID(playlistItemID);
    }
}
