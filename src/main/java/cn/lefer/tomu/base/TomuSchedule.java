package cn.lefer.tomu.base;

import cn.lefer.tomu.base.constant.SongStatus;
import cn.lefer.tomu.base.utils.TomuUtils;
import cn.lefer.tomu.channel.event.ChannelEventService;
import cn.lefer.tomu.song.Song;
import cn.lefer.tomu.song.SongMapper;
import cn.lefer.tools.Net.LeferNet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Slf4j
@Component
public class TomuSchedule {
    @Resource
    private SongMapper songMapper;
    private final AudienceOnlineService audienceOnlineService;
    private final ChannelEventService channelEventService;
    private final static List<SongStatus> DEFAULT_SONG_STATUS_LIST = Arrays.asList(SongStatus.NORMAL, SongStatus.OUTDATE);

    @Autowired
    public TomuSchedule(
            AudienceOnlineService audienceOnlineService,
            ChannelEventService channelEventService) {
        this.audienceOnlineService = audienceOnlineService;
        this.channelEventService = channelEventService;
    }

    //*定时对所有的song的有效性进行验证*//
    @Scheduled(cron = "0 0 3 * * ?")
    public void mp3Check() {
        log.info("开始刷新歌曲状态...");
        List<Song> songs = songMapper.selectAll(DEFAULT_SONG_STATUS_LIST);
        Map<SongStatus, List<Integer>> songStatusSongMap = songs.parallelStream().map(this::updateSongStatus).collect(Collectors.groupingBy(Song::getSongStatus, Collectors.mapping(Song::getSongID, toList())));
        for (SongStatus songStatus : songStatusSongMap.keySet()) {
            int rows = songMapper.batchUpdateSongStatus(songStatus, songStatusSongMap.get(songStatus));
            log.info("歌曲状态刷新：" + songStatus + "-" + rows + "行.");
        }
        log.info("刷新歌曲状态结束.");
    }

    private Song updateSongStatus(Song song) {
        try {
            if (LeferNet.isValid(song.getMp3Url())) {
                song.setSongStatus(SongStatus.NORMAL);
            } else {
                song.setSongStatus(SongStatus.OUTDATE);
            }
        } catch (IOException e) {
            log.warn("定时校验歌曲状态出错:" + song.getSongID() + "-" + e.getMessage());
        }
        return song;
    }

    @Scheduled(cron = "0 0 2 * * ?")
    public void refreshCache() {
        log.info("开始清理缓存...");
        List<String> silenceUsers = audienceOnlineService.clear();
        silenceUsers.stream().map(TomuUtils::getNickname).forEach(channelEventService::delete);
        log.info("缓存清理完毕.");
    }
}
