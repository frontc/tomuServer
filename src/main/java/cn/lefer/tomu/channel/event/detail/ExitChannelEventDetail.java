package cn.lefer.tomu.channel.event.detail;

import lombok.*;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class ExitChannelEventDetail extends AbstractChannelEventDetail{
    String nickName;
}
