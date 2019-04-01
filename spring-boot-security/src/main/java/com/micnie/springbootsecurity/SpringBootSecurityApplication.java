package com.micnie.springbootsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1,引入相关的SpringSecurity
 * 2,编写SpringSecurity的配置类
 *     @EnableWebSecurity
 * public class MySecurityConfig extends WebSecurityConfigurerAdapter
 *
 * 3,控制请求的访问权限：
 *
 */
@SpringBootApplication
public class SpringBootSecurityApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootSecurityApplication.class, args);
    }

}
