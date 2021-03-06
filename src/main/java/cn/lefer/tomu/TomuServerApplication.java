package cn.lefer.tomu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class TomuServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomuServerApplication.class, args);
    }

}
