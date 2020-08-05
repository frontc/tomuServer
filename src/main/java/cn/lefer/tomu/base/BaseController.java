package cn.lefer.tomu.base;

import cn.lefer.tools.Token.LeferJwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.net.InetSocketAddress;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1")
public class BaseController {
    @Value("${tomu.server.version}")
    private String VERSION;
    @Value("${tomu.token.key}")
    private String TOKEN_KEY;
    @Value("${tomu.token.ttMillis}")
    private long TOKEN_TTMIllIS;


    /**
     * get the server's version no
     *
     * @return version
     */
    @GetMapping(value = "/version")
    public Mono<String> getVersion() {
        return Mono.just(VERSION);
    }

    /**
     * log in
     *
     * @return token
     */
    @GetMapping(value = "/auth")
    public Mono<String> auth(ServerWebExchange exchange) {
        String hostString = Optional.ofNullable(exchange.getRequest().getRemoteAddress()).map(InetSocketAddress::getHostString).orElse("");
        return Mono.just(LeferJwt.createToken("tomu", hostString, TOKEN_KEY, TOKEN_TTMIllIS));
    }
}
