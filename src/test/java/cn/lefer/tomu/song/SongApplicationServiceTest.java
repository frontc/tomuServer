package cn.lefer.tomu.song;

import static cn.lefer.tomu.base.constant.SongSource.netease;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class SongApplicationServiceTest {
    @Autowired
    SongApplicationService songApplicationService;

    @Test
    void save() {
        String songName = "Car Park";
        String artistName = "Fenne Lily";
        String songUrl = "https://music.163.com/#/song?id=1307473639";
        String mp3Url = "https://api.i-meto.com/meting/api?server=netease&type=song&id=340383&r=0.023052520560386425";
        String coverUrl = "https://api.i-meto.com/meting/api?server=netease&type=pic&id=109951163105662267&auth=67845de5ba4fff4a715c495ed9f31a9b72ad545b";
        String lrcUrl = "https://api.i-meto.com/meting/api?server=netease&type=lrc&id=340383&auth=620e461301fd513c8dd0b766bbea22d51f912850";
        int songID = songApplicationService.save(songName, artistName, netease, songUrl, mp3Url, coverUrl, lrcUrl);
        Assertions.assertTrue(songID>0);
    }
}