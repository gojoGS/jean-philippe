public class FilterSearchStrategy implements SearchStrategy{
    @Override
    public List<Item> filterSearch(List<Item> hayStack, SearchProperties properties) {
        return hayStack
                .stream()
                .filter(item -> item.getPriceInHuf() >= properties.getMinPrice())
                .filter(item -> item.getPriceInHuf() <= properties.getMaxPrice())
                .filter(item -> item.getName().contains(properties.getName()))
                .toList();
    }
}
