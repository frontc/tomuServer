package cn.lefer.tomu.channel.command;

import lombok.Data;

@Data
public class GetPlayHistoryCommand {
    private int pageNum;
    private int pageSize;
}
