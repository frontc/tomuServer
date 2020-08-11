package cn.lefer.tomu.channel;

import cn.lefer.tomu.base.constant.PlayAction;
import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import cn.lefer.tomu.channel.event.ChannelEvent;
import cn.lefer.tomu.channel.event.ChannelEventService;
import cn.lefer.tomu.channel.event.ChannelEventType;
import cn.lefer.tomu.channel.event.detail.AbstractChannelEventDetail;
import cn.lefer.tomu.channel.event.detail.ChannelPlayStatusChangeEventDetail;
import cn.lefer.tomu.base.queue.MessagePool;
import cn.lefer.tomu.base.queue.MessageType;
import cn.lefer.tomu.base.utils.TomuUtils;
import cn.lefer.tomu.channel.model.*;
import cn.lefer.tools.Date.LeferDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
public class ChannelApplicationService {
    private final ChannelRepository channelRepository;
    private final PlaylistItemRepository playlistItemRepository;
    private final ChannelEventService channelEventService;
    private final MessagePool messagePool;

    public ChannelApplicationService(ChannelRepository channelRepository,
                                     PlaylistItemRepository playlistItemRepository,
                                     ChannelEventService channelEventService,
                                     MessagePool messagePool) {
        this.channelRepository = channelRepository;
        this.playlistItemRepository = playlistItemRepository;
        this.channelEventService = channelEventService;
        this.messagePool = messagePool;
    }

    public int createChannel() {
        Channel channel = Channel.create();
        int channelID = channelRepository.save(channel);
        log.info("Create Channel:" + channelID);
        return channelID;
    }

    public long addPlaylistItem(int channelID, int songID) {
        PlaylistItem playlistItem = PlaylistItem.create(channelID, songID);
        long playlistItemID = playlistItemRepository.save(playlistItem);
        log.info("Create PlaylistItem:" + playlistItemID + " (Song: " + songID + " Channel: " + channelID + ")");
        return playlistItemID;
    }

    public void deletePlaylistItem(int channelID, int songID) {
        Optional<PlaylistItem> playlistItem = Optional.ofNullable(playlistItemRepository.byChannelIDAndSongID(channelID, songID, PlaylistItemStatus.NORMAL));
        playlistItem.ifPresent(playlistItemRepository::delete);
        log.info("Delete PlaylistItem:" + playlistItem.map(PlaylistItem::getPlaylistItemID).orElse(0L) + " (Song: " + songID + " Channel: " + channelID + ")");
    }

    public void changePlayStatus(int channelID, String token, int songID, double position, PlayAction playAction) {
        Date now = LeferDate.today();
        //记入事件队列，对外广播
        ChannelEvent.Builder<ChannelPlayStatusChangeEventDetail> builder = new ChannelEvent.Builder<>();
        ChannelPlayStatusChangeEventDetail detail = new ChannelPlayStatusChangeEventDetail();
        detail.setChannelID(channelID);
        detail.setDate(now);
        detail.setPosition(position);
        detail.setSongID(songID);
        detail.setPlayAction(playAction);
        channelEventService.broadcast(channelID, token, builder.withType(ChannelEventType.CHANGE_PLAY_STATUS).withDetail(detail).build());
        //记入持久化队列，交由异步线程持久化
        PlayHistoryItem playHistoryItem = PlayHistoryItem.builder()
                .channelID(channelID)
                .songID(songID)
                .playDate(now)
                .lastPosition(position).build();
        messagePool.getMessageProducer().onData(MessageType.NEW_PLAY_HISTORY, playHistoryItem);
    }

    public boolean hasNews(String token) {
        return !channelEventService.isEmpty(TomuUtils.getNickname(token));
    }

    public ServerSentEvent<ChannelEvent<? extends AbstractChannelEventDetail>> getNews(int channelID, String token, String seq) {
        ChannelEvent<? extends AbstractChannelEventDetail> channelEvent = channelEventService.get(TomuUtils.getNickname(token));
        log.debug("向频道[" + channelID + "],推送事件:" + channelEvent);
        return ServerSentEvent.<ChannelEvent<? extends AbstractChannelEventDetail>>builder()
                .event(channelEvent.getType().toString())
                .id(seq)
                .data(channelEvent)
                .build();
    }
}