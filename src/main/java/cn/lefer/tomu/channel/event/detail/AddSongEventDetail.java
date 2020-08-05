package cn.lefer.tomu.channel.event.detail;


import cn.lefer.tomu.channel.representation.PlaylistItemRepresentation;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Getter
@Setter
public class AddSongEventDetail extends AbstractChannelEventDetail{
    PlaylistItemRepresentation playlistItemRepresentation;
}
