package cn.lefer.tomu.song;

import cn.lefer.tomu.base.AudienceOnlineService;
import cn.lefer.tools.Token.LeferJwt;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebFluxTest(SongController.class)
@Import({AudienceOnlineService.class, SongRepresentationService.class})
@TestPropertySource("classpath:application-test.properties")
class SongControllerTest {
    @MockBean
    SongRepresentationRepository songRepresentationRepository;
    @Autowired
    WebTestClient client;
    @Value("${tomu.random.size}")
    int random_size;
    @Value("${tomu.token.key}")
    private String TOKEN_KEY;
    @Value("${tomu.token.ttMillis}")
    private long TOKEN_TTMIllIS;
    String authorization;

    @BeforeEach
    void before() {
        authorization = "Bearer " + LeferJwt.createToken("tomu", "TEST", TOKEN_KEY, TOKEN_TTMIllIS);
    }

    @Test
    void getRandomSongs() {
        List<SongRepresentation> songRepresentations = new ArrayList<>();
        SongRepresentation songRepresentation = new SongRepresentation();
        songRepresentation.setArtistName("test");
        songRepresentation.setSongID(10);
        songRepresentation.setSongUrl("test");
        songRepresentations.add(songRepresentation);
        Mockito.when(songRepresentationRepository.getRandomSongs(random_size)).thenReturn(
                songRepresentations
        );
        client.get().uri("/api/v1/song/explore")
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBodyList(SongRepresentation.class).hasSize(songRepresentations.size());
    }

    @Test
    void getSong() {
        int songID = 10;
        SongRepresentation songRepresentation = new SongRepresentation();
        songRepresentation.setArtistName("test");
        songRepresentation.setSongID(songID);
        songRepresentation.setSongUrl("test");
        Mockito.when(songRepresentationRepository.byID(songID)).thenReturn(songRepresentation);

        client.get().uri("/api/v1/song/{songID}", songID)
                .header(HttpHeaders.AUTHORIZATION, authorization)
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody().jsonPath("$.songID").isEqualTo(songID);
    }
}