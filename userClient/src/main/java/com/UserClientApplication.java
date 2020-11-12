package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @description: 用户消费启动类
 * @author: yinminxin
 * @create: 2020-11-05
 **/
@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients
public class UserClientApplication {

//    @Bean
//    public RestTemplate restTemplate() {
//        // 默认的RestTemplate，底层是走JDK的URLConnection方式。
//        return new RestTemplate();
//    }

    public static void main(String[] args) {
        SpringApplication.run(UserClientApplication.class, args);
    }
}
