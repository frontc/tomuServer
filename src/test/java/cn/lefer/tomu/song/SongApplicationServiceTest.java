package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static cn.lefer.tomu.base.constant.SongSource.tencent;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongApplicationServiceTest {
    @Autowired
    SongApplicationService songApplicationService;

    @Transactional
    @Test
    void save() {
        String songName = "天涯歌女";
        String artistName = "周璇";
        String songUrl = "https://music.163.com/#/";
        String mp3Url = "https://api.i-meto.com/meting/api?server=tencent&type=url&id=001zcC9z0uWHv5&auth=2e30346074e79234734964c2000744ff9ef5cae8";
        String coverUrl = "https://api.i-meto.com/meting/api?server=tencent&type=pic&id=000kQjhj18NtMF&auth=78e065f66dfc7d6e6cf39893b22f7f54ba9b7e7d";
        String lrcUrl = "https://api.i-meto.com/meting/api?server=tencent&type=lrc&id=001zcC9z0uWHv5&auth=33e1241b48f785d3c0392d9a006c337a5de1c859";
        int songID = songApplicationService.save(songName, artistName, tencent, songUrl, mp3Url, coverUrl, lrcUrl);
        Assertions.assertTrue(songID>0);
    }
}