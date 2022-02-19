package ru.learnup18.aviasales.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Premiere {

    private String name; //Предпологаем что имя иникально, и является ключом
    private String description;
    private String ageCategory;
    private Integer countFreeSeats;
    private Integer countFreeReal;

}
