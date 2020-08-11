package cn.lefer.tomu.channel.event;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/19
 * @Description : 歌曲来源
 */
public enum ChannelEventType {
    ADD_SONG,/*增加歌曲*/
    DELETE_SONG,/*删除歌曲*/
    AUDIENCE_IN,/*听众进入*/
    AUDIENCE_EXIT,/*听众退出*/
    KICK_AUDIENCE,/*踢走听众*/
    CHANGE_PLAY_STATUS,/*改变播放状态*/
}
