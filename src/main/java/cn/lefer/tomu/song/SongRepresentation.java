package cn.lefer.tomu.song;

import cn.lefer.tomu.base.constant.SongSource;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SongRepresentation {
    int songID;
    SongSource songSource;
    String songUrl;
    String songName;
    String artistName;
    String coverUrl;
    String lrcUrl;
    String mp3Url;
}
