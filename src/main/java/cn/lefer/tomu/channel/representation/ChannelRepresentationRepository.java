package cn.lefer.tomu.channel.representation;


import cn.lefer.tomu.base.Page;
import cn.lefer.tomu.base.constant.GraceDateClassification;
import cn.lefer.tomu.base.constant.PlaylistItemStatus;
import cn.lefer.tomu.base.constant.SongStatus;
import cn.lefer.tomu.channel.exception.ChannelNotExistException;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

@Repository
public class ChannelRepresentationRepository {
    @Resource
    private ChannelRepresentationMapper channelRepresentationMapper;

    public ChannelRepresentation get(int channelID) {
        try{
            ChannelRepresentation channelRepresentation = channelRepresentationMapper.queryChannelByChannelID(channelID);
            channelRepresentation.setPlaylist(channelRepresentationMapper.queryNormalPlaylistByChannelID(channelID, PlaylistItemStatus.NORMAL, SongStatus.NORMAL));
            return channelRepresentation;
        }catch (Exception ex){
            throw new ChannelNotExistException();
        }
    }

    public PlaylistItemRepresentation getPlaylistItemByID(long playlistItemID) {
        return channelRepresentationMapper.queryPlaylistItemByID(playlistItemID);
    }

    public List<PlaylistItemRepresentation> getPlaylistByChannelID(int channelID) {
        return channelRepresentationMapper.queryNormalPlaylistByChannelID(channelID,PlaylistItemStatus.NORMAL,SongStatus.NORMAL);
    }

    public Page<PlayHistoryItemRepresentation> getPlayHistoryByChannelID(int channelID, int pageNum, int pageSize) {
        List<PlayHistoryItemRepresentation> playHistory = channelRepresentationMapper.queryPlayHistoryByChannelIDAndPageNumAndPageSize(channelID,pageNum,pageSize);
        playHistory.forEach(item-> item.setGraceDateClassification(GraceDateClassification.get(item.getPlayDate()).value()));
        long total = channelRepresentationMapper.queryTotalNumOfPlayHistoryByChannelID(channelID);
        Page.Builder<PlayHistoryItemRepresentation> pageBuilder = new Page.Builder<>();
        return pageBuilder.data(playHistory).pageNum(pageNum).pageSize(pageSize).total(total).build();
    }
}
