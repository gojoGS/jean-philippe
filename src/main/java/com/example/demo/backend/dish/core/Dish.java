package com.example.demo.backend.dish.core;

import com.example.demo.backend.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "dishes")
public class Dish extends Item {
    private DishType type;
    @Lob
    private String description;

    public Dish(String name, DishType type, Long priceInHuf, String description) {
        this.name = name;
        this.type = type;
        this.priceInHuf = priceInHuf;
        this.description = description;
    }

    public void update(Dish newState) {
        this.name = newState.getName();
        this.type = newState.getType();
        this.priceInHuf = newState.getPriceInHuf();
        this.description = newState.getDescription();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dish dish = (Dish) o;
        return id != null && Objects.equals(id, dish.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "(" +
                "id = " + id + ", " +
                "name = " + name + ", " +
                "type = " + type + ", " +
                "priceInHuf = " + priceInHuf + ")";
    }
}
