package org.zt25.example;


import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication(scanBasePackages = {"org.zt25"})
@MapperScan(basePackages={"org.zt25.example.mapper"})
public class RunApplication {

    public static void main(String[] args){
        SpringApplication.run(RunApplication.class, args);
        //try {
        //
        //}catch (Exception e){
        //    e.printStackTrace();
        //}
    }

}
