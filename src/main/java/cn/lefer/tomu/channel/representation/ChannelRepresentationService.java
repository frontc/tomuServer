package cn.lefer.tomu.channel.representation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description : channel的查询服务
 */
@Component
public class ChannelRepresentationService {
    private final ChannelRepresentationRepository representationRepository;

    @Autowired
    public ChannelRepresentationService(ChannelRepresentationRepository channelRepresentationRepository){
        this.representationRepository=channelRepresentationRepository;
    }

    public ChannelRepresentation getChannel(int channelID) {
        return representationRepository.get(channelID);
    }

    public PlaylistItemRepresentation getPlaylistItemByID(long playlistItemID) {
        return representationRepository.getPlaylistItemByID(playlistItemID);
    }

    public List<PlaylistItemRepresentation> getPlaylist(int channelID) {
        return representationRepository.getPlaylistByChannelID(channelID);
    }
}
