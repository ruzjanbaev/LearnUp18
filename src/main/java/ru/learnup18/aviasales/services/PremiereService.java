package ru.learnup18.aviasales.services;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import ru.learnup18.aviasales.model.Premiere;
import ru.learnup18.aviasales.services.interfaces.Logger;

import java.util.Arrays;
import java.util.HashMap;

@Service
public class PremiereService implements ApplicationContextAware {

    private Logger logger;
    private ApplicationContext ctx;
    private HashMap<String, Premiere> premiereMap;

    @Autowired
    public PremiereService(Logger logger, HashMap premiereMap) {
        this.logger = logger;
        this.premiereMap = premiereMap;
    }

    //Добавление
    public void insPremiere(Premiere premiere) {
        logger.print("insPremiere: " + premiere.getName());
        premiereMap.put(premiere.getName(), premiere);
    }

    //Удаление
    public void delPremiere(String name) {
        logger.print("delPremiere name: " + name);
        ctx.getBean(TicketService.class).delTicketPremiere(name);
        premiereMap.remove(name);
    }

    //Обновление - Предпологаем что имя Премьеры, это уникальное имя
    public boolean updPremiere(String name, String description, String ageCategory) {
        logger.print("updPremiere: " + Arrays.toString(new String[]{name, description, ageCategory}));
        premiereMap.get(name).setDescription(description);
        premiereMap.get(name).setAgeCategory(ageCategory);
        return true;
    }

    //При покупке или продаже билета, мы уменьшаем или увеличиваем реальное количество
    public boolean incSeatsRealPremiere(String name, Integer incTicket) {
        int freeSeats = premiereMap.get(name).getCountFreeSeats();
        int freeReal = premiereMap.get(name).getCountFreeReal();
        if ((freeReal + incTicket) < 1) {
            logger.print("Ошибка, невозможно сдать билет!");
        } else if ((freeReal + incTicket) > freeSeats) {
            logger.print("Ошибка, невозможно купить билет!");
        } else {
            logger.print("freeSeats: " + freeSeats + ", freeReal: " + freeReal + ", (freeReal + incTicket): " + (freeReal + incTicket));
            premiereMap.get(name).setCountFreeReal(freeReal + incTicket);
        }
        return true;
    }

    public void allToStringPremiere() {
        for (Premiere s : premiereMap.values()) {
            oneToStringPremiere(s.getName(), false);
        }
    }

    public boolean oneToStringPremiere(String name, boolean ticket) {
        System.out.println("--> Премьера(Событие) : " + premiereMap.get(name).getName() +
                ", описание : " + premiereMap.get(name).getDescription() +
                ", возрастная категория : " + premiereMap.get(name).getAgeCategory() +
                ", кол-о дост-х мест : " + premiereMap.get(name).getCountFreeReal() +
                ", изначальное кол-о дост-х мест : " + premiereMap.get(name).getCountFreeSeats());
        if (ticket) {
            ctx.getBean(TicketService.class).allToStringTicket(name);
        }
        return true;
    }

    @Override
    public void setApplicationContext(ApplicationContext ctx) throws BeansException {
        this.ctx = ctx;
    }

}
