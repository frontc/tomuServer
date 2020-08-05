package cn.lefer.tomu.channel.event.detail;

import cn.lefer.tomu.base.constant.PlayAction;
import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ChannelPlayStatusChangeEventDetail extends AbstractChannelEventDetail {
    int songID;
    double position;
    PlayAction playAction;
}
