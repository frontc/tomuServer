package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongStatus;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SongRepresentationMapper {
    List<SongRepresentation> querySomeNormalSongsByRandom(@Param("size") int size, @Param("songStatus") SongStatus songStatus);

    SongRepresentation byID(int songID);
}
