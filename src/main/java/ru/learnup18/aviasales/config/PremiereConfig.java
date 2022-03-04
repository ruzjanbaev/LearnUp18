package ru.learnup18.aviasales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.learnup18.aviasales.model.Premiere;
import java.util.HashMap;

@Configuration
public class PremiereConfig {

    //@Primary
    @Bean
    public HashMap<String, Premiere> premiereMap() {
        HashMap<String, Premiere> premiereMap = new HashMap<>();
        return premiereMap;
    }

}
