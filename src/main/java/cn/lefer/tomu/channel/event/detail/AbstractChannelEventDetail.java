package cn.lefer.tomu.channel.event.detail;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/7/7
 * @Description : 频道事件详情的抽象类
 */
@Data
public abstract class AbstractChannelEventDetail {
    int channelID;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
    Date date;
}
