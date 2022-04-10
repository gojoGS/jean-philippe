package com.example.demo.ui.enduser.view;

import com.example.demo.backend.beverage.core.Beverage;
import com.example.demo.backend.broadcast.Broadcaster;
import com.example.demo.backend.broadcast.Event;
import com.example.demo.backend.broadcast.EventType;
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
import com.vaadin.flow.component.html.H3;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.tabs.PagedTabs;

import java.util.ArrayList;

@Route("app/end-user/order")
public class OrderView extends EndUserViewBase {
    private ArrayList<Dish> dishes;
    private Grid<Dish> dishGrid;
    private ArrayList<Beverage> beverages;
    private Grid<Beverage> beverageGrid;
    private final long sessionId;
    private final H2 orderTotal = new H2("");
    private final EntityService<Dish> dishEntityService;
    private final EntityService<Beverage> beverageEntityService;
    private final SessionService sessionService;
    private final OrderRepository orderRepository;
    private final EndUserDetailsService endUserDetailsService;


    @Autowired
    public OrderView(EntityServiceFactory<Dish> dishEntityServiceFactory,
                     EntityServiceFactory<Beverage> beverageEntityServiceFactory,
                     EndUserDetailsService endUserDetailsService,
                     SessionServiceFactory sessionServiceFactory,
                     OrderRepository orderRepository) {
        super("Ordering");

        var sessionTable = endUserDetailsService.getUser().getTable();
        var restaurantId = sessionTable.getRestaurant().getId();
        this.sessionId = sessionTable.getOrderSession().getId();
        this.dishEntityService = dishEntityServiceFactory.get(restaurantId);
        this.beverageEntityService = beverageEntityServiceFactory.get(restaurantId);
        this.sessionService = sessionServiceFactory.get(sessionTable.getOrderSession().getId());
        this.orderRepository = orderRepository;
        this.dishes = new ArrayList<>();
        this.beverages = new ArrayList<>();
        this.endUserDetailsService = endUserDetailsService;

        var beverageGrid = new Grid<Beverage>(Beverage.class, false);

        beverageGrid.addColumn(Beverage::getName).setHeader("Name");
        beverageGrid.addColumn(Beverage::getPriceInHuf).setHeader("Price (HUF)");
        beverageGrid.addColumn(Beverage::getVolumeInMililiters).setHeader("Volume (ml)");
        beverageGrid.addColumn(Beverage::isAlcoholic).setHeader("Is alcoholic");
        beverageGrid.addColumn(Beverage::isDiet).setHeader("Is diet");
        beverageGrid.addComponentColumn(beverage -> {
            var button = new Button("-");
            button.addClickListener(buttonClickEvent -> this.removeBeverage(beverage));
            return button;
        });
        beverageGrid.addColumn(this::getBeverageCount);
        beverageGrid.addComponentColumn(beverage -> {
            var button = new Button("+");
            button.addClickListener(buttonClickEvent -> this.addBeverage(beverage));
            return button;
        });

        var dishGrid = new Grid<Dish>(Dish.class, false);

        dishGrid.addColumn(Item::getName).setHeader("Name");
        dishGrid.addColumn(Item::getPriceInHuf).setHeader("Price (HUF)");
        dishGrid.addColumn(dish -> dish.getType().getName()).setHeader("Type");
        dishGrid.addComponentColumn(dish -> {
            var button = new Button("-");
            button.addClickListener(buttonClickEvent -> this.removeDish(dish));
            return button;
        });
        dishGrid.addColumn(this::getDishCount);
        dishGrid.addComponentColumn(dish -> {
            var button = new Button("+");
            button.addClickListener(buttonClickEvent -> this.addDish(dish));
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
        beverageGrid.setItems(beverageEntityService.getAll());

        this.dishGrid = dishGrid;
        this.beverageGrid = beverageGrid;

        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);

        tabs.add("Dishes", dishGrid, false);
        tabs.add("Beverages", beverageGrid, false);

        add(
                tabs,
                container,
                orderTotal,
                new HorizontalLayout(
                        new Button("Send order", buttonClickEvent -> sendOrder()),
                        new Button("Cancel")
                )
        );

    }

    private long getDishCount(Dish dish) {
        return this.dishes.stream().filter(item1 -> item1.getId().equals(dish.getId())).count();
    }

    private long getBeverageCount(Beverage beverage) {
        return this.beverages.stream().filter(beverage1 -> beverage1.getId().equals(beverage.getId())).count();
    }

    private void refreshGrid() {
        dishGrid.setItems(dishEntityService.getAll());
        beverageGrid.setItems(beverageEntityService.getAll());
    }

    private void refreshSum() {
        this.orderTotal.setText(
                String.format(
                        "Total: %d",
                        this.dishes.stream().map(Item::getPriceInHuf).reduce(0L, Long::sum) +
                                this.beverages.stream().map(Item::getPriceInHuf).reduce(0L, Long::sum)
                )
        );
    }

    private void addBeverage(Beverage beverage) {
        this.beverages.add(beverage);
        refreshGrid();
        refreshSum();
    }

    private void removeBeverage(Beverage beverage) {
        this.beverages.remove(beverage);
        refreshGrid();
        refreshSum();
    }

    private void addDish(Dish dish) {
        this.dishes.add(dish);
        refreshGrid();
        refreshSum();
    }

    private void removeDish(Dish dish) {
        this.dishes.remove(dish);
        refreshGrid();
        refreshSum();
    }

    private void sendOrder() {
        if(this.dishes.isEmpty() && this.beverages.isEmpty()) {
            NotificationUtil.showInfo("You must select at least one item");
            return;
        }

        var order = new Order();

        var items = new ArrayList<Item>();
        items.addAll(this.dishes);
        items.addAll(this.beverages);

        order.setItems(
                items
        );


        order.setOrderStatus(OrderStatus.WAITING);
        order.setOrderSession(
                endUserDetailsService.getUser().getTable().getOrderSession()
        );

        orderRepository.save(order);

        Broadcaster.broadcast(
                new Event(EventType.ORDER_NEW, sessionId)
        );

        NotificationUtil.showSuccess("Your order has been sent");
        UI.getCurrent().navigate(SentToRestaurantView.class);
    }
}
