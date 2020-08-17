package cn.lefer.tomu.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/5
 * @Description :
 */
@SpringBootTest
@Transactional
@ActiveProfiles("test")
class TomuScheduleTest {
    @Autowired
    TomuSchedule tomuSchedule;

    @Test
    void mp3Check() {
        tomuSchedule.mp3Check();
        Assertions.assertTrue(true);
    }

    @Test
    void refreshCache() {
        tomuSchedule.refreshCache();
        Assertions.assertTrue(true);
    }
}