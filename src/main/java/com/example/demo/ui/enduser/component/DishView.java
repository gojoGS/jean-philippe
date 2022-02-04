package com.example.demo.ui.enduser.component;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.dish.service.DishCrudService;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.vaadin.crudui.crud.impl.GridCrud;

@Route("enduser/dishes")
@AnonymousAllowed
public class DishView extends VerticalLayout {
    public DishView(DishCrudService dishCrudService) {
        var crud = new GridCrud<>(Dish.class, dishCrudService);
        crud.getGrid().setColumns("name", "type", "priceInHuf", "description");
        crud.getCrudFormFactory().setVisibleProperties("name", "type", "priceInHuf", "description");

        add(
                crud
        );

        crud.setAddOperationVisible(false);
        crud.setUpdateOperationVisible(false);
        crud.setDeleteOperationVisible(false);
    }
}

