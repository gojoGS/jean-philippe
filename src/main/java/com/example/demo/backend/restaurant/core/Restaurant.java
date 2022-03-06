package com.example.demo.backend.restaurant.core;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.dish.core.Dish;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Dish.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_dish_fk", referencedColumnName = "id")
    private Set<Dish> dishes;

    @OneToMany(targetEntity = Beverage.class, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "restaurant_beverage_fk", referencedColumnName = "id")
    private Set<Beverage> beverages;

    private String description;

    public Restaurant(String name, String description) {
        this.name = name;
        this.description = description;
        this.dishes = new HashSet<>();
        this.beverages = new HashSet<>();
    }

    public void addDish(Dish dish) {
        dishes.add(dish);
    }

    public void addBeverage(Beverage beverage) {
        beverages.add(beverage);
    }

    public void removeBeverage(Beverage beverage) {
        beverages.removeIf(beverage1 -> Objects.equals(beverage1.getId(), beverage.getId()));
    }

    public void updateBeverage(Beverage beverage) {
        var resultBeverage = beverages
                .stream()
                .filter(beverage1 -> Objects.equals(beverage1.getId(), beverage.getId()))
                .findFirst();

        if (resultBeverage.isEmpty()) {
            // TODO should we throw?
            return;
        }

        resultBeverage.get().update(beverage);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Restaurant that = (Restaurant) o;
        return id != null && Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Restaurant{" +
                "id=" + id +
                ", dishes=" + dishes +
                '}';
    }
}
