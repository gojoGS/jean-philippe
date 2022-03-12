package com.example.demo.ui.restaurant.component;

import lombok.extern.slf4j.Slf4j;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;


@Slf4j
public class CrudComponent<T> extends GridCrud<T> {
    public CrudComponent(CrudListener<T> listener, Class<T> cls, String... properties) {
        super(cls, listener);

        getGrid().setColumns(properties);
        getCrudFormFactory().setVisibleProperties(properties);
    }
}
