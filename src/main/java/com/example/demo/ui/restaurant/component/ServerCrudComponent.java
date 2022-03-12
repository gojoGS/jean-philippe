package com.example.demo.ui.restaurant.component;

import com.example.demo.backend.server.core.Server;
import com.example.demo.backend.table.core.Table;
import org.vaadin.crudui.crud.CrudListener;
import org.vaadin.crudui.crud.impl.GridCrud;

public class ServerCrudComponent extends GridCrud<Server> {
    public ServerCrudComponent(CrudListener<Server> listener) {
        super(Server.class, listener);
        getGrid().setColumns("name");
        getCrudFormFactory().setVisibleProperties("name");
    }
}
