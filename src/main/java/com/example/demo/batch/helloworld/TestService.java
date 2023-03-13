package com.example.demo.batch.helloworld;

import org.springframework.stereotype.Service;

@Service
public class TestService {
    public String helloService() {
        System.out.println(">>>>>>>>>> hello from service");
        return "HELLO SERVICE";
    }
}
