package com.example.demo.security.user.enduser.core;

import com.example.demo.backend.table.core.RestaurantTable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.With;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "end_users")
public class EndUser {
    public EndUser(String userId, String encryptedPassword) {
        this.userId = userId;
        this.encryptedPassword = encryptedPassword;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;
    private String userId;
    private String encryptedPassword;

    @OneToOne(mappedBy = "user")
    private RestaurantTable table;
}
