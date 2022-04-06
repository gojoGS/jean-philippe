// ...
@Entity
@Table(name = "orders")
public class Order {
    // ...
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "order_items", 
            joinColumns = @JoinColumn(name = "order_id"), 
            inverseJoinColumns = @JoinColumn(name = "dish_id"))
    private List<Item> items;
    // ...
}

// ...
@Entity
public class Item {
    // ...
    @ManyToMany(mappedBy = "items")
    Set<Order> orders;
    // ...
}
