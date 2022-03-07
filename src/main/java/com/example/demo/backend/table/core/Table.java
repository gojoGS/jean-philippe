package com.example.demo.backend.table.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Setter
@Entity
@javax.persistence.Table(name = "restaurant_table")
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private long number;
    private int size;
    @Lob
    private String description;

    public void update(Table table) {
        this.size = table.getSize();
        this.number = table.getNumber();
        this.description = table.getDescription();
    }
}
