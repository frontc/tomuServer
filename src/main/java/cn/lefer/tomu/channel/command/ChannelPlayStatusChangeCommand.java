package cn.lefer.tomu.channel.command;

import cn.lefer.tomu.base.constant.PlayAction;
import lombok.Data;

@Data
public class ChannelPlayStatusChangeCommand {
    int songID;
    double position;
    PlayAction playAction;
}
