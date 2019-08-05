package com.geek.life;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

@SpringBootApplication
@MapperScan("com.geek.life.dao")
@EnableSwagger2 // swagger接口工具
@ComponentScan(basePackages = "com.geek.life")
public class LifeApplication {

    public static void main(String[] args) {
        SpringApplication.run(LifeApplication.class, args);
    }

}
