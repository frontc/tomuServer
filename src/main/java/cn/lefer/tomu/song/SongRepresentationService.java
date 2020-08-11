package cn.lefer.tomu.song;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SongRepresentationService {
    private final SongRepresentationRepository songRepresentationRepository;
    @Value("${tomu.random.size}")
    private int TOMU_RANDOM_SIZE;

    public SongRepresentationService(SongRepresentationRepository songRepresentationRepository){
        this.songRepresentationRepository=songRepresentationRepository;
    }

    public List<SongRepresentation> getRandomSongs() {
        return songRepresentationRepository.getRandomSongs(TOMU_RANDOM_SIZE);
    }

    public SongRepresentation getSong(String songID) {
        return songRepresentationRepository.byID(songID);
    }
}
