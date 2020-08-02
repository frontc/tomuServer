package cn.lefer.tomu.song;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/song")
public class SongController {
    //TODO:随机获取歌曲
    public void getRandomSongs(){}
}
