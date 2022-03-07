package com.example.demo.backend.restaurant.service.crud.table;

import com.example.demo.backend.table.core.Table;
import org.vaadin.crudui.crud.CrudListener;

public interface TableCrudServiceFactory {
    CrudListener<Table> get(long restaurantId);
}
