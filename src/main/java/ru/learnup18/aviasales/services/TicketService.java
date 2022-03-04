package ru.learnup18.aviasales.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import ru.learnup18.aviasales.model.Ticket;
import ru.learnup18.aviasales.services.interfaces.Logger;

import java.util.HashMap;

@Service
public class TicketService implements ApplicationContextAware {

    private Logger logger;
    private ApplicationContext ctx;
    private HashMap<String, Ticket> ticketMap;

    @Autowired
    private PremiereService premiereService;

    @Autowired
    public void TicketService(Logger logger, HashMap ticketMap) {
        this.logger = logger;
        this.ticketMap = ticketMap;
    }

    //Добавление - покупка
    public void insTicket(Ticket ticket) {
        logger.print("insTicket: " + ticket.getName());
        // кргда покупк, билет минус один
        if (premiereService.incSeatsRealPremiere(ticket.getPremiereName(), -1)) {
            ticketMap.put(ticket.getName(), ticket);
        }
    }

    //Удаление - сдать билет
    public void delTicket(String name, String premiereName) {
        logger.print("delTicket: " + name);
        if (premiereService.incSeatsRealPremiere(premiereName, 1)) {
            ticketMap.remove(name);
        }
    }
    public void delTicketPremiere(String premiereName) {
        logger.print("delTicket premiereName: " + premiereName);
        if (ticketMap.isEmpty()) {
            ticketMap = ctx.getBean(TicketService.class).ticketMap;
        }
        HashMap<String, Ticket> ticketMapTmp = (HashMap<String, Ticket>) ticketMap.clone();
        for (Ticket s : ticketMapTmp.values()) {
            if (s.getPremiereName() == premiereName) {
                ticketMap.remove(s.getName());
            }
        }
    }

    public void allToStringTicket() {
        for (Ticket s : ticketMap.values()) {
            System.out.println("--> Билет : " + s.getName() + ". Премьера(Событие) : " + s.getPremiereName());
        }
    }

    public void allToStringTicket(String premiereName) {
        if (ticketMap.isEmpty()) {
            ticketMap = ctx.getBean(TicketService.class).ticketMap;
        }
        for (Ticket s : ticketMap.values()) {
            if (s.getPremiereName() == premiereName) {
                System.out.println("----> Билет : " + s.getName() + ". Премьера(Событие) : " + s.getPremiereName());
            }
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

}
