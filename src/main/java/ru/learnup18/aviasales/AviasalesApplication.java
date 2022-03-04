package ru.learnup18.aviasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup18.aviasales.model.Premiere;
import ru.learnup18.aviasales.model.Ticket;
import ru.learnup18.aviasales.services.PremiereService;
import ru.learnup18.aviasales.services.TicketService;

@SpringBootApplication
public class AviasalesApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(AviasalesApplication.class, args);

        System.out.println(":::::::::::::::::::::::: insert Premiere :::::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).insPremiere(new Premiere("Premiere_Name_1", "Premiere description 1", "8-18", 200, 200));
        ctx.getBean(PremiereService.class).insPremiere(new Premiere("Premiere_Name_2", "Premiere description 2", "18-24", 200, 200));
        ctx.getBean(PremiereService.class).insPremiere(new Premiere("Premiere_Name_3", "Premiere description 3", "0-999", 300, 300));
        ctx.getBean(PremiereService.class).allToStringPremiere();
        System.out.println(":::::::::::::::::::::::: insert Ticket :::::::::::::::::::::::::::::::::");
        ctx.getBean(TicketService.class).insTicket(new Ticket("Ticket 11","Premiere_Name_1"));
        ctx.getBean(TicketService.class).insTicket(new Ticket("Ticket 12","Premiere_Name_1"));
        ctx.getBean(TicketService.class).insTicket(new Ticket("Ticket 13","Premiere_Name_1"));
        ctx.getBean(TicketService.class).insTicket(new Ticket("Ticket 21","Premiere_Name_2"));
        ctx.getBean(TicketService.class).insTicket(new Ticket("Ticket 22","Premiere_Name_2"));
        ctx.getBean(TicketService.class).insTicket(new Ticket("Ticket 31","Premiere_Name_3"));
        ctx.getBean(TicketService.class).allToStringTicket();
        System.out.println(":::::::::::::::::::::::: delete Ticket :::::::::::::::::::::::::::::::::");
        ctx.getBean(TicketService.class).delTicket("Ticket 12","Premiere_Name_1");
        ctx.getBean(TicketService.class).allToStringTicket();
        System.out.println("::::::::::::::::::::::::::: update Premiere ::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).updPremiere("Premiere_Name_1", "Premiere description 111", "6-18");
        ctx.getBean(PremiereService.class).allToStringPremiere();
        System.out.println(":::::::::::::::::::::::::: delete Premiere and Ticket :::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).delPremiere("Premiere_Name_2");
        ctx.getBean(PremiereService.class).allToStringPremiere();
        ctx.getBean(TicketService.class).allToStringTicket();
        System.out.println(":::::::::::::::::::: One print Premiere and Ticket :::::::::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).oneToStringPremiere("Premiere_Name_1", true);

    }
}
