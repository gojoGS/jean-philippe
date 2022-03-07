package com.example.demo.backend.restaurant.service.crud.table;

import com.example.demo.backend.restaurant.service.table.TableService;
import com.example.demo.backend.restaurant.service.table.TableServiceFactory;
import com.example.demo.backend.table.core.Table;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.vaadin.crudui.crud.CrudListener;

import java.util.Collection;

@Component
public class TableCrudServiceFactoryImpl implements TableCrudServiceFactory {
    @Autowired
    private TableServiceFactory tableServiceFactory;

    @Override
    public CrudListener<Table> get(long restaurantId) {
        return new TableCrudServiceImpl(restaurantId);
    }

    private class TableCrudServiceImpl implements CrudListener<Table> {
        private final TableService tableService;

        public TableCrudServiceImpl(long restaurantId) {
            this.tableService = tableServiceFactory.get(restaurantId);
        }

        @Override
        public Collection<Table> findAll() {
            return tableService.getAll();
        }

        @Override
        public Table add(Table table) {
            tableService.addTable(table);
            return table;
        }

        @Override
        public Table update(Table table) {
            tableService.update(table);
            return table;
        }

        @Override
        public void delete(Table table) {
            tableService.removeTable(table);
        }
    }
}
