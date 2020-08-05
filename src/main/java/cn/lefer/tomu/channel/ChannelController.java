
package cn.lefer.tomu.channel;

import cn.lefer.tomu.base.AudienceOnlineService;
import cn.lefer.tomu.base.Page;
import cn.lefer.tomu.channel.event.ChannelEvent;
import cn.lefer.tomu.channel.event.ChannelEventService;
import cn.lefer.tomu.channel.event.detail.AbstractChannelEventDetail;
import cn.lefer.tomu.base.utils.TomuUtils;
import cn.lefer.tomu.channel.command.AddSongCommand;
import cn.lefer.tomu.channel.command.ChannelPlayStatusChangeCommand;
import cn.lefer.tomu.channel.command.GetPlayHistoryCommand;
import cn.lefer.tomu.channel.representation.ChannelRepresentation;
import cn.lefer.tomu.channel.representation.ChannelRepresentationService;
import cn.lefer.tomu.channel.representation.PlayHistoryItemRepresentation;
import cn.lefer.tomu.channel.representation.PlaylistItemRepresentation;
import cn.lefer.tomu.song.SongApplicationService;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/channel")
public class ChannelController {
    @Resource
    private ChannelApplicationService channelApplicationService;
    @Resource
    private ChannelRepresentationService channelRepresentationService;
    @Resource
    private SongApplicationService songApplicationService;
    @Resource
    private AudienceOnlineService audienceOnlineService;
    @Resource
    private ChannelEventService channelEventService;

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
    public Mono<ChannelRepresentation> getChannel(@PathVariable("channelID") @Validated int channelID,
                                                  ServerWebExchange exchange) {
        ChannelRepresentation channelRepresentation = channelRepresentationService.getChannel(channelID);
        channelEventService.publishAudienceInEvent(channelID,TomuUtils.getToken(exchange));
        return Mono.just(channelRepresentation);
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
                                                             @Validated AddSongCommand addSongCommand,
                                                             ServerWebExchange exchange) {
        //1.添加到歌曲库
        int songID = songApplicationService.save(addSongCommand.getSongName(),
                addSongCommand.getArtistName(),
                addSongCommand.getSongSource(),
                addSongCommand.getSongUrl(),
                addSongCommand.getMp3Url(),
                addSongCommand.getCoverUrl(),
                addSongCommand.getLrcUrl());
        //2.添加到频道歌单
        long playlistItemID = channelApplicationService.addPlaylistItem(channelID, songID);
        PlaylistItemRepresentation playlistItemRepresentation = channelRepresentationService.getPlaylistItemByID(playlistItemID);
        //3.广播消息
        channelEventService.publishSongAddEvent(channelID,TomuUtils.getToken(exchange),playlistItemRepresentation);
        //4.返回歌单项目
        return Mono.just(playlistItemRepresentation);
    }

    /**
     * remove a song from a channel
     */
    @DeleteMapping(value = "/{channelID}/song/{songID}")
    public void removeSongFromChannel(@PathVariable("channelID") int channelID, @PathVariable("songID") int songID,
                                      ServerWebExchange exchange) {
        channelApplicationService.deletePlaylistItem(channelID, songID);
        channelEventService.publishSongRemoveEvent(channelID,songID,TomuUtils.getToken(exchange));
    }

    /**
     * report the channel's play status
     *
     * @param command songID,position,PLAY/PAUSE status
     */
    @PostMapping(value = "/{channelID}/event/playStatusChange", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public void reportChannelPlayStatus(@PathVariable int channelID,
                                        ServerWebExchange exchange,
                                        @Validated ChannelPlayStatusChangeCommand command) {
        channelApplicationService.changePlayStatus(channelID, TomuUtils.getToken(exchange), command.getSongID(), command.getPosition(), command.getPlayAction());
    }

    /**
     * publish news to client
     *
     * @param clientID clientID alias token
     * @return ServerSentEvent<ChannelEvent<? extends AbstractChannelEventDetail>>
     */
    @GetMapping(value = "/{channelID}/event",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<ServerSentEvent<ChannelEvent<? extends AbstractChannelEventDetail>>> broadcastChannelStatus(@PathVariable("channelID") @Validated int channelID,
                                                                                                            @RequestParam @Validated String clientID) {
        return Flux.interval(Duration.ofMillis(100))
                .filter(seq -> channelApplicationService.hasNews(clientID))
                .map(seq -> channelApplicationService.getNews(channelID, clientID, Long.toString(seq)));
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
        return Mono.just(channelRepresentationService.getPlayHistory(channelID, getPlayHistoryCommand.getPageNum(), getPlayHistoryCommand.getPageSize()));
    }

    /**
     * get channel's audience
     *
     * @return audience lists
     */
    @GetMapping(value = "/{channelID}/audience")
    public List<String> getAudience(@PathVariable("channelID") @Validated int channelID) {
        return audienceOnlineService.getAudienceWithNickName(channelID);
    }

    /**
     * audience leave from a channel
     */
    @DeleteMapping(value = "/{channelID}/audience")
    public void audienceExitFromChannel(@PathVariable("channelID") @Validated int channelID, ServerWebExchange exchange) {
        audienceOnlineService.exit(channelID, TomuUtils.getToken(exchange));
        channelEventService.publishAudienceOutEvent(channelID, TomuUtils.getToken(exchange));
    }

    /**
     * audience kick away a channel
     */
    @DeleteMapping(value = "/{channelID}/audience/{nickName}")
    public void kickOthers(@PathVariable("channelID") @Validated int channelID,
                           @PathVariable("nickName") String nickName) {
        audienceOnlineService.getAudienceWithFullNameByNickname(channelID,nickName).ifPresent(token->channelEventService.publishAudienceOutEvent(channelID,token));
        audienceOnlineService.kick(channelID, nickName);
    }
}
