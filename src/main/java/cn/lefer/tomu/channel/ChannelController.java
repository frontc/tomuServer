
package cn.lefer.tomu.channel;

import cn.lefer.tomu.base.Page;
import cn.lefer.tomu.channel.command.AddSongCommand;
import cn.lefer.tomu.channel.command.GetPlayHistoryCommand;
import cn.lefer.tomu.channel.representation.ChannelRepresentation;
import cn.lefer.tomu.channel.representation.ChannelRepresentationService;
import cn.lefer.tomu.channel.representation.PlayHistoryItemRepresentation;
import cn.lefer.tomu.channel.representation.PlaylistItemRepresentation;
import cn.lefer.tomu.song.SongApplicationService;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;

@RestController
@RequestMapping(value = "/api/v1/channel")
public class ChannelController {
    @Resource
    private ChannelApplicationService channelApplicationService;
    @Resource
    private ChannelRepresentationService channelRepresentationService;
    @Resource
    private SongApplicationService songApplicationService;

    /**
     * Create a channel
     *
     * @return int. the channel id
     */
    @PostMapping(value = "")
    public Mono<Integer> createChannel() {
        return Mono.just(channelApplicationService.createChannel());
    }

    /**
     * enter a channel
     *
     * @return ChannelRepresentation. the channel info
     */
    @GetMapping(value = "/{channelID}")
    public Mono<ChannelRepresentation> getChannel(@PathVariable("channelID") @Validated int channelID) {
        return Mono.just(channelRepresentationService.getChannel(channelID));
    }

    //TODO:分页获取频道歌单
    public void getSongsByPageInChannel() {
    }

    /**
     * get channel's playlist
     *
     * @return Flux<PlaylistItemRepresentation>. the channel's playlist
     */
    @GetMapping(value = "/{channelID}/songs/all")
    public Flux<PlaylistItemRepresentation> getSongsInChannel(@PathVariable("channelID") int channelID) {
        return Flux.fromStream(channelRepresentationService.getPlaylist(channelID).stream());
    }

    /**
     * add a song to a channel
     *
     * @param addSongCommand the info of a song
     * @return PlaylistItemRepresentation.
     */
    @PostMapping(value = "/{channelID}/song", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<PlaylistItemRepresentation> addSongToChannel(@PathVariable("channelID") @Validated int channelID,
                                                             @Validated AddSongCommand addSongCommand) {
        //1.添加歌曲
        int songID = songApplicationService.save(addSongCommand.getSongName(),
                addSongCommand.getArtistName(),
                addSongCommand.getSongSource(),
                addSongCommand.getSongUrl(),
                addSongCommand.getMp3Url(),
                addSongCommand.getCoverUrl(),
                addSongCommand.getLrcUrl());
        //2.添加到歌单
        long playlistItemID = channelApplicationService.addPlaylistItem(channelID,songID);
        //3.返回歌单项目
        return Mono.just(channelRepresentationService.getPlaylistItemByID(playlistItemID));
    }

    /**
     * remove a song from a channel
     *
     */
    @DeleteMapping(value = "/{channelID}/song/{songID}")
    public void removeSongFromChannel(@PathVariable("channelID") int channelID, @PathVariable("songID") int songID) {
        channelApplicationService.deletePlaylistItem(channelID,songID);
    }

    //TODO:上报频道状态变化
    public void reportChannelStatus() {
    }

    //TODO:广播频道状态变化
    public void broadcastChannelStatus() {
    }

    /**
     * get channel's play history
     *
     * @param getPlayHistoryCommand page size and page number;
     * @return Page<PlayHistoryItemRepresentation>
     */
    @GetMapping(value = "/{channelID}/playHistory", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public Mono<Page<PlayHistoryItemRepresentation>> getPlayHistoryInChannel(@PathVariable int channelID,
                                                                             @Validated GetPlayHistoryCommand getPlayHistoryCommand) {
        return Mono.just(channelRepresentationService.getPlayHistory(channelID,getPlayHistoryCommand.getPageNum(),getPlayHistoryCommand.getPageSize()));
    }
}
