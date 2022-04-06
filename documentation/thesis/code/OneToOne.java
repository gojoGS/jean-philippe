// ...
@Entity
@Table(name = "restaurant_table")
public class RestaurantTable {
    // ...
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "restaurant_table_id")
    private EndUser user;
    // ...
}

// ...
@Entity
@Table(name = "end_users")
public class EndUser {
    // ...
    @OneToOne(mappedBy = "user")
    private RestaurantTable table;
    // ...
}
