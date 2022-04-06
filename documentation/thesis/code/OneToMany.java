// ...
@Entity
@Table(name = "orders")
public class Order {
    // ...
    @ManyToOne
    @JoinColumn(name = "server_id")
    private Server server;
    // ...
}
