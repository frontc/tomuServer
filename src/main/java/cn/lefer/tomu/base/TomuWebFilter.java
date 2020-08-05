package cn.lefer.tomu.base;

import cn.lefer.tomu.base.exception.InvalidTokenException;
import cn.lefer.tomu.base.exception.NoTokenException;
import cn.lefer.tomu.base.utils.TomuUtils;
import cn.lefer.tools.Token.LeferJwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/29
 * @Description : 基于Jwt的过滤器，实现token的颁发及在线状态的记录
 */
@Slf4j
@Configuration
public class TomuWebFilter implements WebFilter {
    @Value("${tomu.token.key}")
    String TOKEN_KEY;
    AudienceOnlineService audienceOnlineService;

    public TomuWebFilter(AudienceOnlineService audienceOnlineService) {
        this.audienceOnlineService = audienceOnlineService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange serverWebExchange, WebFilterChain webFilterChain) {
        ServerHttpRequest request = serverWebExchange.getRequest();
        String path = request.getPath().value();
        log.debug(request.getId() + " - " + path);
        //特殊处理SSE接口
        if (HttpMethod.GET.equals(request.getMethod()) && path.contains("event")) {
            return webFilterChain.filter(serverWebExchange);
        }
        if (path.contains("channel")) {
            //token校验
            String token = TomuUtils.getToken(serverWebExchange);
            if (token == null) throw new NoTokenException();
            if (!LeferJwt.isValid(token, TOKEN_KEY)) throw new InvalidTokenException();
            //获取本次请求的ChannelID
            int channelID = getChannelID(path.split("/"));
            //记录访客的频道
            if (channelID > 0) audienceOnlineService.updateOnlineStatus(token, channelID);
        }
        return webFilterChain.filter(serverWebExchange);
    }

    private int getChannelID(String[] pathList) {
        int channelID = -1;
        for (int index = 0; index < pathList.length; index++) {
            if (pathList[index].equals("channel") && index < pathList.length - 1) {
                channelID = Integer.parseInt(pathList[index + 1]);
                break;
            }
        }
        return channelID;
    }
}
