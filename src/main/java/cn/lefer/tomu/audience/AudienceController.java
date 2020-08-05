package cn.lefer.tomu.audience;

import cn.lefer.tomu.base.TomuUtils;
import lombok.ToString;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ServerWebExchange;

import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/audience")
public class AudienceController {
    /**
     * get current audience's nick name
     *
     * @return nickname
     */
    @GetMapping(value = "/nickname")
    public String who(ServerWebExchange exchange) {
        return Optional.ofNullable(TomuUtils.getToken(exchange)).map(TomuUtils::getNickname).orElse("anonymous");
    }
    //TODO:获取频道下的听众
    public void getAudienceInChannel(){}
    //TODO:听众退出频道
    public void audienceExitFromChannel(){}
    //TODO:将听众从频道中踢走
    public void kickAudienceAway(){}
}
