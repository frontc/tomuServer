-- 1.t_channel
drop table if exists t_channel;
create table t_channel
(
    channel_id       int IDENTITY primary key,
    channel_name     varchar(50),
    channel_owner_id int,
    channel_key      varchar(50),
    channel_crt_date datetime
);
-- 2.t_song
drop table if exists  t_song;
create table t_song
(
    song_id       int IDENTITY primary key,
    song_name     varchar(200),
    song_duration decimal,
    artist_name   varchar(50),
    song_source   varchar(20),
    song_url      varchar(255),
    mp3_url       varchar(255),
    cover_url     varchar(255),
    lrc_url       varchar(255),
    song_status   varchar(20),
    song_add_date datetime
);
-- 3.t_playlist
drop table if exists t_playlist;
create table t_playlist
(
    playlist_item_id  bigint IDENTITY primary key,
    channel_id int,
    song_id int,
    add_date datetime,
    playlist_item_status varchar(20),
    foreign key (channel_id) references t_channel(channel_id),
    foreign key (song_id) references t_song(song_id)
);
CREATE INDEX idx_t_channel_song_channel_id
    ON t_playlist (channel_id);
CREATE INDEX idx_t_channel_song_song_id
    ON t_playlist (song_id);
CREATE INDEX idx_t_channel_song_valid_flag
    ON t_playlist (playlist_item_status);
-- 4.t_play_history
drop table if exists  t_play_history;
create table t_play_history
(
    play_history_item_id bigint IDENTITY primary key,
    channel_id      int,
    song_id         int,
    last_position   decimal,
    play_date       datetime
);

CREATE INDEX idx_t_play_history_channel_id
    ON t_play_history (channel_id);

CREATE INDEX idx_t_play_history_play_date
    ON t_play_history (play_date);