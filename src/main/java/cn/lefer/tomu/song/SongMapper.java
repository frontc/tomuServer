package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/24
 * @Description : song mapper
 */
@Mapper
public interface SongMapper {
    int insert(Song song);
    Song byID(@Param("songID") int songID);
    Song bySongNameAndArtistNameOrMP3Url(@Param("songName") String songName,
                                         @Param("artistName") String artistName,
                                         @Param("mp3Url") String mp3Url);

    List<Song> selectAll(@Param("songStatusList") List<SongStatus> songStatusList);

    int batchUpdateSongStatus(@Param("songStatus") SongStatus songStatus, @Param("songIDs") List<Integer> songIDs);
}
