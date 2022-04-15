// ...
public class RestaurantMenuView extends RestaurantViewBase {
    @Autowired
    public RestaurantMenuView(EntityServiceFactory<Dish> dishEntityServiceFactory, /* ... */) {
        super("Menu", "Menu");

        var container = new VerticalLayout();
        var tabs = new PagedTabs(container);

        var dishCrud = new CrudComponent<>(
                new EntityServiceAdapter<>(dishEntityServiceFactory.get(restaurantId)),
                Dish.class,
                dishCrudProperties
        );
        tabs.add("Dishes", dishCrud, false);
        
        // ...

        this.add(
                tabs,
                container
        );
    }
}
