package cn.lefer.tomu.audience;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v2/audience")
public class AudienceController {
    //TODO:获取自己的昵称
    public void whoAmI(){}
    //TODO:获取频道下的听众
    public void getAudienceInChannel(){}
    //TODO:听众退出频道
    public void audienceExitFromChannel(){}
    //TODO:将听众从频道中踢走
    public void kickAudienceAway(){}
}
