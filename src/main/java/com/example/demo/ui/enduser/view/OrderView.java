package com.example.demo.ui.enduser.view;

import com.example.demo.backend.dish.core.Dish;
import com.example.demo.backend.item.core.Item;
import com.example.demo.backend.order.core.Order;
import com.example.demo.backend.order.core.OrderStatus;
import com.example.demo.backend.order.repository.OrderRepository;
import com.example.demo.backend.restaurant.service.base.EntityService;
import com.example.demo.backend.restaurant.service.base.EntityServiceFactory;
import com.example.demo.backend.session.service.SessionService;
import com.example.demo.backend.session.service.SessionServiceFactory;
import com.example.demo.security.user.enduser.service.details.EndUserDetailsService;
import com.example.demo.ui.util.NotificationUtil;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.dialog.Dialog;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Route("app/end-user/order")
public class OrderView extends EndUserViewBase {
    private ArrayList<Dish> dishes;
    private Grid<Dish> dishGrid;
    private final H2 orderTotal = new H2("");
    private final EntityService<Dish> dishEntityService;
    private final SessionService sessionService;
    private final OrderRepository orderRepository;
    private final EndUserDetailsService endUserDetailsService;


    @Autowired
    public OrderView(EntityServiceFactory<Dish> dishEntityServiceFactory,
                     EndUserDetailsService endUserDetailsService,
                     SessionServiceFactory sessionServiceFactory,
                     OrderRepository orderRepository) {
        super("Ordering");

        var sessionTable = endUserDetailsService.getUser().getTable();
        var restaurantId = sessionTable.getRestaurant().getId();
        this.dishEntityService = dishEntityServiceFactory.get(restaurantId);
        this.sessionService = sessionServiceFactory.get(sessionTable.getOrderSession().getId());
        this.orderRepository = orderRepository;
        this.dishes = new ArrayList<>();
        this.endUserDetailsService = endUserDetailsService;

        var dishGrid = new Grid<Dish>(Dish.class, false);

        dishGrid.addColumn(Item::getName).setHeader("Name");
        dishGrid.addColumn(Item::getPriceInHuf).setHeader("Price (HUF)");
        dishGrid.addColumn(dish -> dish.getType().getName()).setHeader("Type");
        dishGrid.addComponentColumn(dish -> {
            var button = new Button("-");
            button.addClickListener(buttonClickEvent -> this.removeItem(dish));
            return button;
        });
        dishGrid.addColumn(this::getItemCount);
        dishGrid.addComponentColumn(dish -> {
            var button = new Button("+");
            button.addClickListener(buttonClickEvent -> this.addItem(dish));
            return button;
        });

        dishGrid.addItemClickListener(dishItemClickEvent -> {
            var clickedDish = dishItemClickEvent.getItem();
            var closeButton = new Button("Close");

            var dialogContent = new VerticalLayout(
                    new H1(clickedDish.getName()),
                    new Text(clickedDish.getDescription()),
                    closeButton
            );

            dialogContent.setJustifyContentMode(JustifyContentMode.CENTER);
            dialogContent.setAlignItems(Alignment.CENTER);

            var descriptionDialog = new Dialog(
                    dialogContent
            );

            closeButton.addClickListener(buttonClickEvent -> descriptionDialog.close());
            descriptionDialog.open();
        });

        dishGrid.setItems(dishEntityService.getAll());

        this.dishGrid = dishGrid;

        add(
                dishGrid,
                orderTotal,
                new HorizontalLayout(
                        new Button("Send order", buttonClickEvent -> sendOrder()),
                        new Button("Cancel")
                )
        );

    }

    private long getItemCount(Dish dish) {
        return this.dishes.stream().filter(item1 -> item1.getId().equals(dish.getId())).count();
    }

    private void refreshGrid() {
        dishGrid.setItems(dishEntityService.getAll());
    }

    private void refreshSum() {
        this.orderTotal.setText(
                String.format(
                        "Total: %d",
                        this.dishes.stream().map(Item::getPriceInHuf).reduce(0L, Long::sum)
                )
        );
    }

    private void addItem(Dish dish) {
        this.dishes.add(dish);
        refreshGrid();
        refreshSum();
    }

    private void removeItem(Dish dish) {
        this.dishes.remove(dish);
        refreshGrid();
        refreshSum();
    }

    private void sendOrder() {
        if(this.dishes.isEmpty()) {
            NotificationUtil.showInfo("You must select at eleast one item");
        }

        var order = new Order();

            order.setItems(
                    new ArrayList<>(this.dishes)
            );


        order.setOrderStatus(OrderStatus.WAITING);
        order.setOrderSession(
                endUserDetailsService.getUser().getTable().getOrderSession()
        );

        orderRepository.save(order);

        NotificationUtil.showSuccess("Your order has been sent");
        UI.getCurrent().navigate(SentToRestaurantView.class);
    }
}
