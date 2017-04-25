package com.hmtool.appsvc.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@Configuration
//@ComponentScan(basePackageClasses = Application.class)
//@EnableAutoConfiguration
@SpringBootApplication
//@EnableJpaRepositories
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
