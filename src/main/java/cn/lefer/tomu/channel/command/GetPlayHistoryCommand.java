package cn.lefer.tomu.channel.command;

import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class GetPlayHistoryCommand {
    @Min(value = 1,message = "pageNum must bigger then 1")
    private int pageNum;
    @Min(value = 1,message = "pageSize must bigger then 1")
    private int pageSize;
}
