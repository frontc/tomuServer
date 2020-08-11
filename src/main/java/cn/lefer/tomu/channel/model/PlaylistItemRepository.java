package cn.lefer.tomu.channel.model;


import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import cn.lefer.tomu.channel.exception.PlaylistItemAlreadyExistException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class PlaylistItemRepository {
    @Resource
    private PlaylistItemMapper playlistItemMapper;

    public PlaylistItem byChannelIDAndSongID(int channelID,int songID,PlaylistItemStatus playlistItemStatus){
        return playlistItemMapper.byChannelIDAndSongID(channelID,songID,playlistItemStatus);
    }

    public long save(PlaylistItem playlistItem) {
        PlaylistItem item = playlistItemMapper.selectNormalPlaylistItemByChannelIDAndSongID(playlistItem.getChannelID(),playlistItem.getSongID(), PlaylistItemStatus.NORMAL);
        if(item!=null) throw new PlaylistItemAlreadyExistException();
        playlistItemMapper.insert(playlistItem);
        return playlistItem.getPlaylistItemID();
    }

    public void delete(PlaylistItem playlistItem) {
        playlistItemMapper.updateStatusByID(playlistItem.getPlaylistItemID(),PlaylistItemStatus.DELETE);
    }
}
