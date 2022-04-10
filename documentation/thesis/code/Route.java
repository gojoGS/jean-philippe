@Route("app/restaurant/staff")
public class RestaurantStaffView extends RestaurantViewBase {
    private final String[] crudProperties = {"name"};

    @Autowired
    public RestaurantStaffView(EntityServiceFactory<Server> serverEntityServiceFactory) {
        super("Staff", "Staff");
        // ...
    }
}
