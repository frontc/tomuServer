package cn.lefer.tomu.channel.model;

import cn.lefer.tools.Date.LeferDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
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
    * 5. add a play history
    * */
    public static Channel create(){
        return Channel.builder().channelCreateDate(LeferDate.today())
                .build();
    }
}
