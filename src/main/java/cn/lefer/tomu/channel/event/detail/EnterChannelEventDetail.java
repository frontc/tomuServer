package cn.lefer.tomu.channel.event.detail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class EnterChannelEventDetail extends AbstractChannelEventDetail{
    String nickName;
}
