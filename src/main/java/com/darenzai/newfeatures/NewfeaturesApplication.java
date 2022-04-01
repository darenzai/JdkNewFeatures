package com.darenzai.newfeatures;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class NewfeaturesApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewfeaturesApplication.class, args);
    }

}
