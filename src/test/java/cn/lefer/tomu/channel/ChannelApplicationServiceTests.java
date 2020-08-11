package cn.lefer.tomu.channel;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/1
 * @Description : 测试频道领域应用服务
 */
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class ChannelApplicationServiceTests {
    @Autowired
    ChannelApplicationService applicationService;

    @Test
    void testCreate(){
        int firstID = applicationService.createChannel();
        int secondID= applicationService.createChannel();
        Assertions.assertTrue(secondID>firstID);
    }
}
