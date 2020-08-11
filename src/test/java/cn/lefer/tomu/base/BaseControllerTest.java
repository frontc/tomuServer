package cn.lefer.tomu.base;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebFluxTest(BaseController.class)
@TestPropertySource("classpath:application-test.properties")
@Import(AudienceOnlineService.class)
class BaseControllerTest {

    @Value("${tomu.server.version}")
    String version;
    @Autowired
    WebTestClient client;

    @Test
    void getVersion() throws Exception {
        client.get().uri("/api/v1/version").exchange().expectBody(String.class).isEqualTo(version);
    }

    @Test
    void auth() {
        client.get().uri("/api/v1/auth").exchange().expectStatus().is2xxSuccessful();
    }

    @Test
    void who() {
        client.get().uri("/api/v1/who").exchange().expectBody(String.class);
    }
}