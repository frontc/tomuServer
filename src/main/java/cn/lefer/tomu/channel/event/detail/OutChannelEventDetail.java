package cn.lefer.tomu.channel.event.detail;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class OutChannelEventDetail extends AbstractChannelEventDetail{
    String poorAudience;
}
