package com.example.demo;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import messenger.Messenger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

@SpringBootApplication
@ComponentScans({@ComponentScan(value = "com.example.demo"), @ComponentScan(value = "messenger")})
public class DemoApplication {

    @Autowired
    @Qualifier("BeanPostProcessorExample")
    private Messenger beanPostProcessorExample;

    @Autowired
    @Qualifier("PhasesExample")
    private Messenger phasesExample;


    @Autowired
    @Qualifier("BeanFactoryPostProcessorExample")
    private Messenger beanFactoryPostProcessor;

    @SneakyThrows
    public static void main(String[] args) {

        ConfigurableApplicationContext configurableApplicationContext = SpringApplication.run(DemoApplication.class, args);

//        phasesExample.sayHello();

//        while (true) {
//            Messenger messenger = configurableApplicationContext.getBean(Messenger.class);
//            Thread.sleep(1000);
//            beanPostProcessorExample.sayHello();
//        }
    }

    @PostConstruct
    void init() {

//        phasesExample.sayHello();
        beanFactoryPostProcessor.sayHello();
    }

}
