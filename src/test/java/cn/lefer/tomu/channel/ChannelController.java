
package cn.lefer.tomu.channel;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/channel")
public class ChannelController {

    //TODO:创建频道
    public void createChannel() {
    }

    //TODO:进入频道
    public void getChannel() {
    }

    //TODO:分页获取频道歌单
    public void getSongsByPageInChannel() {
    }

    //TODO:获取频道下所有歌单
    public void getSongsInChannel() {
    }

    //TODO:频道下新增歌曲
    public void addSongToChannel() {
    }

    //TODO:频道下删除歌曲
    public void removeSongFromChannel() {
    }

    //TODO:上报频道状态变化
    public void reportChannelStatus() {
    }

    //TODO:广播频道状态变化
    public void broadcastChannelStatus() {
    }

    //TODO:获取频道的播放历史
    public void getPlayHistoryInChannel() {
    }

}
