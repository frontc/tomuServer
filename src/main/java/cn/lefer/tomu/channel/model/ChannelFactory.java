package cn.lefer.tomu.channel.model;

import org.springframework.stereotype.Component;

@Component
public class ChannelFactory {
    public Channel create(){
        return Channel.create();
    }
}
