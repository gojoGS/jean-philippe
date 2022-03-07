package com.example.demo.ui.restaurant.component;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.table.core.Table;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

public class TableCrudComponent extends GridCrud<Table> {
    public TableCrudComponent(CrudListener<Table> listener) {
        super(Table.class, listener);
        getGrid().setColumns("number", "size", "description");
        getCrudFormFactory().setVisibleProperties("number", "size", "description");
    }
}
