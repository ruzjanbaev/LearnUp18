package ru.learnup18.aviasales.services;

import org.springframework.stereotype.Component;
import ru.learnup18.aviasales.services.interfaces.Logger;

@Component
public class MyLogger implements Logger {

    @Override
    public void print(Object obj) {
        System.out.println("log :: " + obj.toString());
    }
}
