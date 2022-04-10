package com.example.demo.ui.jeanphilippe.view;

import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.item.repository.ItemRepository;
import com.example.demo.backend.item.service.ItemService;
import com.example.demo.backend.restaurant.repository.RestaurantRepository;
import com.example.demo.backend.search.FilterSearchStrategy;
import com.example.demo.backend.search.FilterSearchStrategyFactory;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;


@Route("app/public/search")
public class SearchView extends JeanPhilippeViewBase{
    private FilterSearchStrategyFactory searchStrategyFactory;
    private ItemRepository itemRepository;
    private ItemService itemService;
    private RestaurantRepository restaurantRepository;

    TextField nameTextField = new TextField();
    TextField minPriceTextField = new TextField();
    TextField maxPriceTextField = new TextField();
    Button searchButton = new Button("Search");

    Grid<Item> itemGrid = new Grid(Item.class, false);

    @Autowired
    public SearchView(FilterSearchStrategyFactory searchStrategyFactory,
                      ItemRepository itemRepository,
                      ItemService itemService,
                      RestaurantRepository restaurantRepository) {
        super("Search");

        this.searchStrategyFactory = searchStrategyFactory;
        this.itemRepository = itemRepository;
        this.itemService = itemService;
        this.restaurantRepository = restaurantRepository;

        nameTextField.setPlaceholder("Name");
        minPriceTextField.setPlaceholder("Minimum price");
        maxPriceTextField.setPlaceholder("Maximum price");

        var searchFields = new HorizontalLayout(
                nameTextField,
                minPriceTextField,
                maxPriceTextField,
                searchButton
        );

        searchFields.setSpacing(true);
        searchFields.setJustifyContentMode(JustifyContentMode.CENTER);
        searchFields.setAlignItems(Alignment.END);

        searchButton.addClickListener(buttonClickEvent -> search());

        itemGrid.addColumn(Item::getName).setHeader("Name");
        itemGrid.addColumn(Item::getPriceInHuf).setHeader("Price");
        itemGrid.addColumn(item ->  {
            var restaurants = restaurantRepository.findAll();

            for (var r: restaurants) {
                for(var b: r.getBeverages()) {
                    if(b.getId().equals(item.getId())) {
                        return r.getName();
                    }
                }

                for(var d: r.getDishes()) {
                    if(d.getId().equals(item.getId())) {
                        return r.getName();
                    }
                }

            }
            return "";
        }).setHeader("Restaurant");

        itemGrid.setItems(itemRepository.findAll());

        add(
                searchFields,
                itemGrid
        );
    }

    public void search() {
        String name = nameTextField.getValue();
        long minPrice;

        try {
            minPrice = Long.parseLong(minPriceTextField.getValue());
        } catch (NumberFormatException e) {
            minPrice = Long.MIN_VALUE;
        }

        long maxPrice;

        try {
            maxPrice = Long.parseLong(maxPriceTextField.getValue());
        } catch (NumberFormatException e) {
            maxPrice = Long.MAX_VALUE;
        }

        var properties = new FilterSearchStrategy.SearchProperties(name, minPrice, maxPrice);

        var searchStrategy = searchStrategyFactory.get(properties);

        var result  = searchStrategy.filterSearch(itemRepository.findAll());

        itemGrid.setItems(result);
    }
}
