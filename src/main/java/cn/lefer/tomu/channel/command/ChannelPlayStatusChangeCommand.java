package cn.lefer.tomu.channel.command;

import cn.lefer.tomu.base.constant.PlayAction;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class ChannelPlayStatusChangeCommand {
    @Min(value = 1, message = "The Song ID is Wrong")
    int songID;
    double position;
    PlayAction playAction;
}
