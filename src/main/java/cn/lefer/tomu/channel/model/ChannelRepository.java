package cn.lefer.tomu.channel.model;


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
}
