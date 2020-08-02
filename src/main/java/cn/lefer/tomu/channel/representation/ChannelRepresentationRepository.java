package cn.lefer.tomu.channel.representation;


import cn.lefer.tomu.channel.exception.ChannelNotExistException;
import cn.lefer.tomu.channel.model.Channel;
import cn.lefer.tomu.channel.model.ChannelMapper;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

@Repository
public class ChannelRepresentationRepository {
    @Resource
    private ChannelMapper channelMapper;

    public int save(Channel channel) {
        channelMapper.insert(channel);
        return channel.getChannelID();
    }

    public ChannelRepresentation get(int channelID) {
        try{
            ChannelRepresentation channelRepresentation = channelMapper.queryChannelByChannelID(channelID);
            channelRepresentation.setPlaylist(channelMapper.queryPlaylistByChannelID(channelID));
            return channelRepresentation;
        }catch (Exception ex){
            throw new ChannelNotExistException();
        }
    }
}
