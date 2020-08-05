package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongStatus;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

@Component
public class SongRepresentationRepository {
    @Resource
    SongRepresentationMapper songRepresentationMapper;
    public List<SongRepresentation> getRandomSongs(int size) {
        return songRepresentationMapper.querySomeNormalSongsByRandom(size, SongStatus.NORMAL);
    }
}
