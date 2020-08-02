package cn.lefer.tomu;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TomuServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(TomuServerApplication.class, args);
    }

}
