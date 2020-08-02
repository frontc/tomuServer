package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongSource;
import cn.lefer.tomu.song.exception.SongCannotReachableException;
import cn.lefer.tomu.song.exception.SongVerificationFailedException;
import cn.lefer.tools.Net.LeferNet;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/2
 * @Description :
 */
@Service
public class SongApplicationService {
    @Resource
    SongMapper songMapper;

    public int save(String songName,
                    String artistName,
                    SongSource songSource,
                    String songUrl,
                    String mp3Url,
                    String coverUrl,
                    String lrcUrl){
        try{
            if (!LeferNet.isValid(mp3Url)) throw new SongVerificationFailedException();
        }catch (Exception exception){
            throw new SongCannotReachableException();
        }
        Song song = songMapper.bySongNameAndArtistNameOrMP3Url(songName,artistName,mp3Url);
        if(song!=null) return song.getSongID();
        song=Song.builder().songName(songName)
    }
}
