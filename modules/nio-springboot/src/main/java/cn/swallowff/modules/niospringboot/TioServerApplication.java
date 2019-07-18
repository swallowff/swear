package cn.swallowff.modules.niospringboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.core.starter.annotation.EnableTioServerServer;

/**
 * @author Administrator
 * @description
 * @create 2019/7/18
 */
@SpringBootApplication
@EnableTioServerServer
public class TioServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TioServerApplication.class, args);
    }
}
