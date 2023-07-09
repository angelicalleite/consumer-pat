package br.com.alelo.consumer.consumerpat.enums;

import lombok.Getter;

@Getter
public enum EstablishmentTypeEnum {

    FOOD(1, "FOOD"),
    DRUGSTORE(2, "DRUGSTORE"),
    FUEL(3, "FUEL");

    private final Integer id;
    private final String name;

    EstablishmentTypeEnum(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

}
