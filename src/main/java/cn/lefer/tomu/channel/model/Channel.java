package cn.lefer.tomu.channel.model;

import cn.lefer.tools.Date.LeferDate;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
public class Channel {
    int channelID;
    String channelName;
    int channelOwnerID;
    String channelKey;
    Date channelCreateDate;
    /*
    * the use case of channel:
    * 1. create
    * 2. delete
    * 3. playlist add a song
    * 4. playlist remove a song
    * 5. add a play history
    * */
    public static Channel create(){
        return Channel.builder().channelCreateDate(LeferDate.today())
                .build();
    }
}
