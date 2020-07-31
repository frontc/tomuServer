package cn.lefer.tomu.channel;

import cn.lefer.tomu.channel.model.Channel;
import cn.lefer.tomu.channel.model.ChannelFactory;
import cn.lefer.tomu.channel.repository.ChannelRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ChannelApplicationService {
    ChannelFactory channelFactory;
    ChannelRepository channelRepository;

    @Autowired
    public ChannelApplicationService(ChannelFactory channelFactory,
                                     ChannelRepository channelRepository){
        this.channelFactory=channelFactory;
        this.channelRepository=channelRepository;
    }

    public int createChannel() {
        Channel channel = channelFactory.create();
        int channelID =  channelRepository.save(channel);
        log.info("Create Channel:"+channelID);
        return channelID;
    }
}