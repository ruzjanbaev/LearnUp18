package ru.learnup18.aviasales.services;

import org.springframework.stereotype.Service;
import ru.learnup18.aviasales.model.Premiere;
import ru.learnup18.aviasales.services.interfaces.Logger;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class PremiereService {

    private Logger logger;
    static private HashMap<String, Premiere> premiereArrayList =  new HashMap<String, Premiere>();

    //@Autowired
    public PremiereService(Logger logger) {
        this.logger = logger;
    }

    //Добавление
    public Premiere insPremiere(String name, String description, String ageCategory, Integer countFreeSeats) {
        logger.print("insPremiere: " + name);
        Premiere premiere = new Premiere(name, description, ageCategory, countFreeSeats, countFreeSeats);
        premiereArrayList.put(name, premiere);
        return premiere;
    }

    //Удаление
    public boolean delPremiere(String name) {
        logger.print("delPremiere name: " + name);
        TicketService ticketService = new TicketService(logger);
        ticketService.delTicketPremiere(name);
        premiereArrayList.remove(name);
        return true;
    }

    //Обновление - Предпологаем что имя Премьеры, это уникальное имя
    public boolean updPremiere(String name, String description, String ageCategory) {
        logger.print("updPremiere: " + Arrays.toString(new String[]{name, description, ageCategory}));
        premiereArrayList.get(name).setDescription(description);
        premiereArrayList.get(name).setAgeCategory(ageCategory);
        return true;
    }

    //При покупке или продаже билета, мы уменьшаем или увеличиваем реальное количество
    public boolean incSeatsRealPremiere(String name, Integer incTicket) {
        int freeSeats = premiereArrayList.get(name).getCountFreeSeats();
        int freeReal = premiereArrayList.get(name).getCountFreeReal();
        if ((freeReal + incTicket) < 1) {
            logger.print("Ошибка, невозможно сдать билет!");
        } else if ((freeReal + incTicket) > freeSeats) {
            logger.print("Ошибка, невозможно купить билет!");
        } else {
            logger.print("freeSeats: " + freeSeats + ", freeReal: " + freeReal + ", (freeReal + incTicket): " + (freeReal + incTicket));
            premiereArrayList.get(name).setCountFreeReal(freeReal + incTicket);
            return true;
        }
        return false;
    }

    public boolean allToStringPremiere() {
        for (Premiere s : premiereArrayList.values()) {
            oneToStringPremiere(s.getName(), false);
        }
        return true;
    }

    public boolean oneToStringPremiere(String name, boolean ticket) {
        //System.out.println("premiereArrayList: " + premiereArrayList.toString());
        System.out.println("--> Премьера(Событие) : " + premiereArrayList.get(name).getName() +
                ", описание : " + premiereArrayList.get(name).getDescription() +
                ", возрастная категория : " + premiereArrayList.get(name).getAgeCategory() +
                ", кол-о дост-х мест : " + premiereArrayList.get(name).getCountFreeReal() +
                ", изначальное кол-о дост-х мест : " + premiereArrayList.get(name).getCountFreeSeats());
        if (ticket) {
            TicketService ticketService = new TicketService(logger);
            ticketService.allToStringTicket(name);
        }
        return true;
    }
}
