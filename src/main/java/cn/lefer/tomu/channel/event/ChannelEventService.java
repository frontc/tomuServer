package cn.lefer.tomu.channel.event;

import cn.lefer.tomu.base.AudienceOnlineService;
import cn.lefer.tomu.base.utils.TomuUtils;
import cn.lefer.tomu.channel.event.detail.*;
import cn.lefer.tomu.channel.representation.PlaylistItemRepresentation;
import cn.lefer.tools.Date.LeferDate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/7/7
 * @Description : 频道事件处理类
 */
@Component
@Slf4j
public class ChannelEventService {
    //改变思路，这里不再存放这个用户产生的事件，而是存放这个用户需要处理的事件。
    private final ConcurrentHashMap<String, Queue<ChannelEvent<? extends AbstractChannelEventDetail>>> cache;
    private final AudienceOnlineService audienceOnlineService;

    public ChannelEventService(AudienceOnlineService audienceOnlineService) {
        cache = new ConcurrentHashMap<>();
        this.audienceOnlineService = audienceOnlineService;
    }

    /*添加一个事件*/
    public void add(String key, ChannelEvent<? extends AbstractChannelEventDetail> channelEvent) {
        if (cache.get(key) == null) {
            Queue<ChannelEvent<? extends AbstractChannelEventDetail>> queue = new LinkedList<>();
            queue.offer(channelEvent);
            cache.put(key, queue);
        } else {
            cache.get(key).offer(channelEvent);
        }
        log.debug("新增事件:" + key + "-" + channelEvent.toString());
    }

    /*消费一个事件*/
    public ChannelEvent<? extends AbstractChannelEventDetail> get(String key) {
        if (cache.get(key) == null) {
            return null;
        } else {
            log.debug("事件弹出：" + key + "-" + cache.get(key).peek());
            return cache.get(key).poll();
        }
    }

    /*删除一个client的所有事件*/
    public void delete(String key) {
        cache.remove(key);
    }

    /*探测是否有待处理的事件*/
    public boolean isEmpty(String key) {
        return cache.getOrDefault(key, new LinkedList<>()).peek() == null;
    }

    /*向频道下的其他用户发送一个广播*/
    public void broadcast(int channelID, String currentToke, ChannelEvent<? extends AbstractChannelEventDetail> channelEvent) {
        List<String> audience = audienceOnlineService.getAudienceWithNickName(channelID);
        String currentNickName = TomuUtils.getNickname(currentToke);
        audience.stream()
                .filter(aud -> !aud.equals(currentNickName))
                .forEach(aud -> add(aud, channelEvent));
    }

    /*发布观众进入频道事件*/
    public void publishAudienceInEvent(int channelID, String token) {
        EnterChannelEventDetail detail = new EnterChannelEventDetail();
        detail.setChannelID(channelID);
        detail.setDate(LeferDate.today());
        detail.setNickName(TomuUtils.getNickname(token));
        ChannelEvent.Builder<EnterChannelEventDetail> builder = new ChannelEvent.Builder<>();
        broadcast(channelID, token, builder.withType(ChannelEventType.AUDIENCE_IN).withDetail(detail).build());
    }

    /*发布观众退出频道事件*/
    public void publishAudienceOutEvent(int channelID, String token) {
        ExitChannelEventDetail detail = new ExitChannelEventDetail();
        detail.setDate(LeferDate.today());
        detail.setChannelID(channelID);
        detail.setNickName(TomuUtils.getNickname(token));
        ChannelEvent.Builder<ExitChannelEventDetail> builder = new ChannelEvent.Builder<>();
        broadcast(channelID, token, builder.withType(ChannelEventType.AUDIENCE_OUT).withDetail(detail).build());
    }

    /*发布歌单添加歌曲事件*/
    @Async
    public void publishSongAddEvent(int channelID, String token, PlaylistItemRepresentation playlistItemRepresentation) {
        AddSongEventDetail detail = new AddSongEventDetail();
        detail.setChannelID(channelID);
        detail.setDate(LeferDate.today());
        detail.setPlaylistItemRepresentation(playlistItemRepresentation);
        ChannelEvent.Builder<AddSongEventDetail> builder = new ChannelEvent.Builder<>();
        broadcast(channelID, token, builder.withType(ChannelEventType.ADD_SONG).withDetail(detail).build());
    }

    /*发布歌单删除歌曲事件*/
    @Async
    public void publishSongRemoveEvent(int channelID, int songID, String token) {
        DeleteSongEventDetail detail = new DeleteSongEventDetail();
        detail.setSongID(songID);
        detail.setChannelID(channelID);
        detail.setDate(LeferDate.today());
        ChannelEvent.Builder<DeleteSongEventDetail> builder = new ChannelEvent.Builder<>();
        broadcast(channelID, token, builder.withType(ChannelEventType.DELETE_SONG).withDetail(detail).build());
    }
}
