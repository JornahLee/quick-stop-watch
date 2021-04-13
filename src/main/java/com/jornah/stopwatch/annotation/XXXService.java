package com.jornah.stopwatch.annotation;


import com.jornah.stopwatch.annotation.WatchTime;
import org.springframework.stereotype.Component;

@Component
public class XXXService {

    @WatchTime
    public void fun(){
        System.out.println("fun");
    }

}