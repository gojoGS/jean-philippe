package com.example.demo.backend.dish.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DishType {
    PIZZA("Pizza"),
    SOUP("Soup"),
    MAIN_COURSE("Main course"),
    SIDES("Sides"),
    SALAD("Salad"),
    DESSERT("Dessert");

    private final String name;

}
