package com.jornah.stopwatch;


import com.jornah.stopwatch.annotation.XXXService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class XXXServiceTest {
    @Autowired
    XXXService aop;

    @Test
    void fun() {
        for (int i = 0; i < 10; i++) {
            aop.fun();
        }
    }
}