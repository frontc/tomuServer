package cn.lefer.tomu.channel.model;


import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import cn.lefer.tomu.channel.exception.PlaylistItemAlreadyExistException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ChannelRepository {
    @Resource
    private ChannelMapper channelMapper;

    public int save(Channel channel) {
        channelMapper.insert(channel);
        return channel.getChannelID();
    }

    public Channel byID(int channelID){
        return channelMapper.byID(channelID);
    }

    public long addPlaylistItem(PlaylistItem playlistItem) {
        PlaylistItem item = channelMapper.selectNormalPlaylistItemByChannelIDAndSongID(playlistItem.getChannelID(),playlistItem.getSongID(), PlaylistItemStatus.NORMAL);
        if(item!=null) throw new PlaylistItemAlreadyExistException();
        channelMapper.insertPlaylistItem(playlistItem);
        return playlistItem.getPlaylistItemID();
    }
}
