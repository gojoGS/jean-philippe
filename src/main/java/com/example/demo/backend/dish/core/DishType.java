package com.example.demo.backend.dish.core;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DishType {
    SOUP("Soup"),
    MAIN_COURSE("Main course"),
    SALAD("Salad"),
    DESSERT("Dessert");

    private final String name;
}
