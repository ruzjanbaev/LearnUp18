package ru.learnup18.aviasales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Ticket {
    private String name; //Ключь и имя
    private String premiereName; //Ссылка на примьеру
}
