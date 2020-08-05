package cn.lefer.tomu.base;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/8/5
 * @Description :
 */
@SpringBootTest
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