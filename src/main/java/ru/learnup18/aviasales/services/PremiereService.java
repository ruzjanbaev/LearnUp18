package ru.learnup18.aviasales.services;

import org.springframework.stereotype.Service;
import ru.learnup18.aviasales.model.Premiere;
import ru.learnup18.aviasales.services.interfaces.Logger;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class PremiereService {

    private Logger logger;
    static private ArrayList<Premiere> premiereArrayList = null;

    //@Autowired
    public PremiereService(Logger logger) {
        this.logger = logger;
    }

    public Premiere insPremiere(String name, String description, String ageCategory, Integer countFreeSeats) {
        logger.print("insPremiere: " + name);
        Premiere premiere = new Premiere(name, description, ageCategory, countFreeSeats, countFreeSeats);
        if (premiereArrayList == null) {
            premiereArrayList = new ArrayList<Premiere>();
        }
        premiereArrayList.add(premiere);
        return premiere;
    }

    //Предпологаем что имя Премьеры, это уникальное имя
    public boolean updPremiere(String name, String description, String ageCategory) {
        logger.print("updPremiere: " + Arrays.toString(new String[]{name, description, ageCategory}));
        int i = searPremiere(name);
        if (i >= 0) {
            premiereArrayList.get(i).setDescription(description);
            premiereArrayList.get(i).setAgeCategory(ageCategory);
        }
        return true;
    }

    //При покупке или продаже билета, мы уменьшаем или увеличиваем реальное количество
    public boolean incSeatsRealPremiere(String name, Integer incTicket) {
        //System.out.println("name = "+name);
        int i = searPremiere(name);
        //System.out.println("searPremiere i = " + i);
        if (i >= 0) {
            int freeSeats = premiereArrayList.get(i).getCountFreeSeats();
            int freeReal = premiereArrayList.get(i).getCountFreeReal();
            if ((freeReal + incTicket) < 1) {
                logger.print("Ошибка, невозможно сдать билет!");
            } else if ((freeReal + incTicket) > freeSeats) {
                logger.print("Ошибка, невозможно купить билет!");
            } else {
                logger.print("freeSeats: " + freeSeats + ", freeReal: " + freeReal + ", (freeReal + incTicket): " + (freeReal + incTicket));
                premiereArrayList.get(i).setCountFreeReal(freeReal + incTicket);
                return true;
            }
        }
        return false;
    }

    public Integer searPremiere(String name) {
        //System.out.println("premiereArrayList.size() = " + premiereArrayList.size());
        for (int i = 0; i < premiereArrayList.size(); i++) {
            if (premiereArrayList.get(i).getName() == name) {
                return i;
            }
        }
        return null;
    }

    public boolean delPremiere(String name) {
        logger.print("delPremiere name: " + name);
        for (int i = 0; i < premiereArrayList.size(); i++) {
            if (premiereArrayList.get(i).getName() == name) {
                premiereArrayList.remove(i);
            }
        }
        return true;
    }

    public boolean allToStringPremiere() {
        //System.out.println("premiereArrayList: " + premiereArrayList.toString());
        for (int i = 0; i < premiereArrayList.size(); i++) {
            oneToStringPremiere(premiereArrayList.get(i).getName(), false);
        }
        return true;
    }

    public boolean oneToStringPremiere(String name, boolean ticket) {
        //System.out.println("premiereArrayList: " + premiereArrayList.toString());
        int i = searPremiere(name);
        System.out.println("--> Премьера(Событие) : " + premiereArrayList.get(i).getName() +
                ", описание : " + premiereArrayList.get(i).getDescription() +
                ", возрастная категория : " + premiereArrayList.get(i).getAgeCategory() +
                ", кол-о дост-х мест : " + premiereArrayList.get(i).getCountFreeReal() +
                ", изначальное кол-о дост-х мест : " + premiereArrayList.get(i).getCountFreeSeats());
        if (ticket) {
            TicketService ticketService = new TicketService(logger);
            ticketService.allToStringTicket(name);
        }
        return true;
    }
}
