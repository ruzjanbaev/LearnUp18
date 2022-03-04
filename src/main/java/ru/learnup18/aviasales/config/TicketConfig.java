package ru.learnup18.aviasales.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.learnup18.aviasales.model.Ticket;
import java.util.HashMap;

@Configuration
public class TicketConfig {

    @Bean
    public HashMap<String, Ticket> ticketMap(){
        HashMap<String, Ticket> ticketMap = new HashMap<>();
        return ticketMap;
    }

}
