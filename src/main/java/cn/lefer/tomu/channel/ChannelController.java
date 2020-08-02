
package cn.lefer.tomu.channel;

import cn.lefer.tomu.channel.command.AddSongCommand;
import cn.lefer.tomu.channel.representation.ChannelRepresentation;
import cn.lefer.tomu.channel.representation.ChannelRepresentationService;
import cn.lefer.tomu.channel.representation.PlaylistItemRepresentation;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/v1/channel")
public class ChannelController {
    @Resource
    private ChannelApplicationService applicationService;
    @Resource
    private ChannelRepresentationService representationService;

    /**
     * Create a channel
     *
     * @return int. the channel id
     */
    @PostMapping(value = "")
    public Mono<Integer> createChannel() {
        return Mono.just(applicationService.createChannel());
    }

    /**
     * enter a channel
     *
     * @return ChannelRepresentation. the channel info
     */
    @GetMapping(value = "/{channelID}")
    public Mono<ChannelRepresentation> getChannel(@PathVariable("channelID") @Validated int channelID) {
        return Mono.just(representationService.getChannel(channelID));
    }

    //TODO:分页获取频道歌单
    public void getSongsByPageInChannel() {
    }

    //TODO:获取频道下所有歌单
    public void getSongsInChannel() {
    }

    /**
     * add a song to a channel
     * @param addSongCommand the info of a song
     * @return PlaylistItemRepresentation.
     */
    @PostMapping(value = "/{channelID}/song", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<PlaylistItemRepresentation> addSongToChannel(@PathVariable("channelID") @Validated int channelID,
                                                             @Validated AddSongCommand addSongCommand) {
        //1.添加歌曲
        //2.添加到歌单
        //3.返回歌单项目
        applicationService.addSongToChannel(channelID,addSongCommand);
        return Mono.just(null);
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
