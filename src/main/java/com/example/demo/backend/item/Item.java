package com.example.demo.backend.item;

import com.example.demo.backend.order.core.Order;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Getter
@Setter
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    protected Long priceInHuf;
    protected String name;

    @ManyToMany(mappedBy = "items")
    Set<Order> orders;
}
