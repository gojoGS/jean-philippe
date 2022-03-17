package com.example.demo.security.user.restaurant.core;


import com.example.demo.backend.restaurant.core.Restaurant;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "restaurant_users")
public class RestaurantUser {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String email;
    private String encryptedPassword;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_id")
    private Restaurant restaurant;

    public RestaurantUser(String email, String encryptedPassword, Restaurant restaurant) {
        this.email = email;
        this.encryptedPassword = encryptedPassword;
        this.restaurant = restaurant;
    }
}
