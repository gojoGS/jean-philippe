@AllArgsConstructor
@Getter
public class SearchProperties {
    private String name;
    private long minPrice;
    private long maxPrice;
}

@FunctionalInterface
public interface SearchStrategy {
    List<Item> filterSearch(List<Item> hayStack, SearchProperties properties);
}
