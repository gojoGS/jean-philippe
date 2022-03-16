package com.example.demo.backend.table.core;

import com.example.demo.backend.restaurant.core.Restaurant;
import com.example.demo.security.user.enduser.core.EndUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long number;
    private int size;
    @Lob
    private String description;

    // TOPIC Bidirectional one-to-one
    @OneToOne(cascade = CascadeType.ALL)
    private EndUser user;

    public void update(RestaurantTable restaurantTable) {
        this.size = restaurantTable.getSize();
        this.number = restaurantTable.getNumber();
        this.description = restaurantTable.getDescription();
    }
}
