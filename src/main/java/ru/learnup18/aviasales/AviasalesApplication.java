package ru.learnup18.aviasales;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import ru.learnup18.aviasales.services.*;

@SpringBootApplication
public class AviasalesApplication {

    public static void main(String[] args) {
        final ConfigurableApplicationContext ctx = SpringApplication.run(AviasalesApplication.class, args);

        //((PremiereService) ctx.getBean("PremSrv")).getPremiereByName("Premiere_Name_1");

        ctx.getBean(PremiereService.class).insPremiere("Premiere_Name_1", "Premiere description 1", "0-16", 100);
        ctx.getBean(PremiereService.class).insPremiere("Premiere_Name_2", "Premiere description 2", "18-24", 200);
        ctx.getBean(PremiereService.class).insPremiere("Premiere_Name_3", "Premiere description 3", "0-999", 300);
        ctx.getBean(PremiereService.class).allToStringPremiere();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        ctx.getBean(TicketService.class).insTicket("Ticket 11","Premiere_Name_1");
        ctx.getBean(TicketService.class).insTicket("Ticket 12","Premiere_Name_1");
        ctx.getBean(TicketService.class).insTicket("Ticket 13","Premiere_Name_1");
        ctx.getBean(TicketService.class).insTicket("Ticket 21","Premiere_Name_2");
        ctx.getBean(TicketService.class).insTicket("Ticket 22","Premiere_Name_2");
        ctx.getBean(TicketService.class).insTicket("Ticket 31","Premiere_Name_3");
        ctx.getBean(TicketService.class).allToStringTicket();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        ctx.getBean(TicketService.class).delTicket("Ticket 12","Premiere_Name_1");
        ctx.getBean(TicketService.class).allToStringTicket();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).updPremiere("Premiere_Name_1", "Premiere description 111", "6-18");
        ctx.getBean(PremiereService.class).allToStringPremiere();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).delPremiere("Premiere_Name_2");
        ctx.getBean(PremiereService.class).allToStringPremiere();
        System.out.println(":::::::::::::::::::::::::::::::::::::::::::::::::::::::::");
        ctx.getBean(PremiereService.class).oneToStringPremiere("Premiere_Name_1", true);

    }

}
