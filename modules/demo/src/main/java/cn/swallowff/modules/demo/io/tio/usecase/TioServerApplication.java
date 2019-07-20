package cn.swallowff.modules.demo.io.tio.usecase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.tio.core.starter.annotation.EnableTioServerServer;

/**
 * @author Administrator
 * @description nio-springboot使用自动配置直接启动
 * @create 2019/7/18
 */
@SpringBootApplication
@EnableTioServerServer
public class TioServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(TioServerApplication.class, args);
    }
}
