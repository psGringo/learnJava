package com.stanley.springMagic;

import com.stanley.springMagic.quaters.Quoter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringMagicApplication {

    public static void main(String[] args) throws InterruptedException {


        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("context.xml");
        while (true) {
            Thread.sleep(1000);
            context.getBean(Quoter.class).sayQuote();
        }


//        SpringApplication.run(SpringMagicApplication.class, args);
    }

}
