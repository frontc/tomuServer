package cn.lefer.tomu.channel;

import cn.lefer.tomu.channel.exception.ChannelNotExistException;
import cn.lefer.tomu.channel.model.Channel;
import cn.lefer.tomu.channel.model.ChannelRepository;
import cn.lefer.tomu.channel.model.PlaylistItem;
import cn.lefer.tomu.channel.model.PlaylistItemRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class ChannelApplicationService {
    private final ChannelRepository channelRepository;
    private final PlaylistItemRepository playlistItemRepository;

    @Autowired
    public ChannelApplicationService(ChannelRepository channelRepository,
                                     PlaylistItemRepository playlistItemRepository) {
        this.channelRepository = channelRepository;
        this.playlistItemRepository = playlistItemRepository;
    }

    public int createChannel() {
        Channel channel = Channel.create();
        int channelID = channelRepository.save(channel);
        log.info("Create Channel:" + channelID);
        return channelID;
    }

    public long addPlaylistItem(int channelID, int songID) {
        PlaylistItem playlistItem = PlaylistItem.create(channelID,songID);
        long playlistItemID = playlistItemRepository.save(playlistItem);
        log.info("Create PlaylistItem:"+playlistItemID+" (Song: "+ songID+" Channel: "+channelID+")");
        return playlistItemID;
    }

    public void deletePlaylistItem(int channelID, int songID) {
        Optional<PlaylistItem> playlistItem = Optional.ofNullable(playlistItemRepository.byChannelIDAndSongID(channelID,songID));
        playlistItem.ifPresent(playlistItemRepository::delete);
        log.info("Delete PlaylistItem:"+playlistItem.map(PlaylistItem::getPlaylistItemID).orElse(0L)+" (Song: "+ songID+" Channel: "+channelID+")");
    }
}