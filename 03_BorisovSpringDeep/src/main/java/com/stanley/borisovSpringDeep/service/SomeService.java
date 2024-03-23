package com.stanley.borisovSpringDeep.service;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SomeService {
//
//    @Autowired
//    private SomeInfraBean someInfraBean;

    @PostConstruct
    public void testInfrabean() {
//        someInfraBean.sayHello();

    }


    @Value("${mymessage}")
    private void init(String msg) {
        System.out.println(msg);
    }
}
