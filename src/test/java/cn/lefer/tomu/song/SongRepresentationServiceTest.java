package cn.lefer.tomu.song;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class SongRepresentationServiceTest {
    @Autowired
    SongRepresentationService songRepresentationService;

    @Test
    void getRandomSongs() {
        Assertions.assertTrue(songRepresentationService.getRandomSongs().size()>0);
    }
}