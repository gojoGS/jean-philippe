package com.example.demo.backend.restaurant.service.table;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.table.core.Table;

import java.util.Collection;

public interface TableService {
    void addTable(Table table);

    Collection<Table> getAll();

    void update(Table table);

    void removeTable(Table table);
}
