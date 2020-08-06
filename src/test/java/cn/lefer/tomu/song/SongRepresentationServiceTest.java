package cn.lefer.tomu.song;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SongRepresentationServiceTest {
    @Autowired
    SongRepresentationService songRepresentationService;

    @Test
    void getRandomSongs() {
        Assertions.assertTrue(songRepresentationService.getRandomSongs().size()>0);
    }
}