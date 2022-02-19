package ru.learnup18.aviasales.services;

import org.springframework.stereotype.Service;
import ru.learnup18.aviasales.model.Ticket;
import ru.learnup18.aviasales.services.interfaces.Logger;

import java.util.ArrayList;

@Service
public class TicketService {

    private Logger logger;
    static private ArrayList<Ticket> ticketArrayList = null;

    //@Autowired
    public TicketService(Logger logger) {
        this.logger = logger;
    }

    public Ticket insTicket(String name, String premiereName) {
        logger.print("insTicket: " + name);
        Ticket ticket = null;
        PremiereService premiereService = new PremiereService(logger);
        // кргда покупк, билет минус один
        if (premiereService.incSeatsRealPremiere(premiereName, -1)) {
            ticket = new Ticket(name, premiereName);
            if (ticketArrayList == null) {
                ticketArrayList = new ArrayList<Ticket>();
            }
            ticketArrayList.add(ticket);
        }
        return ticket;
    }

    public boolean delTicket(String name, String premiereName) {
        logger.print("delTicket: " + name);
        for (int i = 0; i < ticketArrayList.size(); i++) {
            if (ticketArrayList.get(i).getName() == name) {
                PremiereService premiereService = new PremiereService(logger);
                if (premiereService.incSeatsRealPremiere(premiereName, 1)) {
                    ticketArrayList.remove(i);
                }
            }
        }
        return true;
    }

    public boolean allToStringTicket() {
        //System.out.println("allToStringTicket: " + ticketArrayList.toString());
        for (int i = 0; i < ticketArrayList.size(); i++) {
            System.out.println("-->  Билет : " + ticketArrayList.get(i).getName() + ". Премьера(Событие) : " + ticketArrayList.get(i).getPremiereName());
        }
        return true;
    }
    public boolean allToStringTicket(String premiereName) {
        //System.out.println("allToStringTicket: " + ticketArrayList.toString());
        for (int i = 0; i < ticketArrayList.size(); i++) {
            if (ticketArrayList.get(i).getPremiereName() == premiereName) {
                System.out.println("---->  Билет : " + ticketArrayList.get(i).getName() + ". Премьера(Событие) : " + ticketArrayList.get(i).getPremiereName());
            }
        }
        return true;
    }
}
