package com.example.demo.backend.beverage.core;

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
@Table(name = "beverages")
public class Beverage extends Item {
    private boolean isAlcoholic;
    private boolean isDiet;
    private int volumeInMililiters;

    public Beverage(String name, boolean isAlcoholic, boolean isDiet, Long priceInHuf, int volumeInMililiters) {
        this.name = name;
        this.isAlcoholic = isAlcoholic;
        this.isDiet = isDiet;
        this.priceInHuf = priceInHuf;
        this.volumeInMililiters = volumeInMililiters;
    }

    public void update(Beverage beverage) {
        this.name = beverage.getName();
        this.isAlcoholic = beverage.isAlcoholic();
        this.isDiet = beverage.isDiet();
        this.priceInHuf = beverage.getPriceInHuf();
        this.volumeInMililiters = beverage.getVolumeInMililiters();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Beverage beverage = (Beverage) o;
        return id != null && Objects.equals(id, beverage.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "Beverage{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isAlcoholic=" + isAlcoholic +
                ", isDiet=" + isDiet +
                ", priceInHuf=" + priceInHuf +
                ", volumeInMililiters=" + volumeInMililiters +
                '}';
    }
}
