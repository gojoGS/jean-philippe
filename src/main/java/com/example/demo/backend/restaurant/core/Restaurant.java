package com.example.demo.backend.restaurant.core;

import com.example.demo.backend.dish.core.Dish;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurants")
public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @OneToMany(targetEntity = Dish.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_dish_fk", referencedColumnName = "id")
    private List<Dish> dishes;

    public void addDish(Dish dish) {
        dishes.add(dish);
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
