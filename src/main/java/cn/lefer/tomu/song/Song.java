package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongSource;
import cn.lefer.tomu.base.constant.SongStatus;
import lombok.*;

import java.util.Date;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Song {
    /*
     * the use case of song:
     * 1. refresh song status
     * 2. random choose some songs.(not in here)
     * */
    int songID;
    String songName;
    Double songDuration;
    String artistName;
    SongSource songSource;
    String songUrl;
    String mp3Url;
    String coverUrl;
    String lrcUrl;
    SongStatus songStatus;
    Date songAddDate;
}
