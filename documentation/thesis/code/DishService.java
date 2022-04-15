@AllArgsConstructor
private class DishServiceImpl implements EntityService<Dish> {
    private final long id;

    private Restaurant getRestaurant() {
        var result = restaurantRepository.findById(id);

        if (result.isEmpty()) {
            log.error(String.format("restaurant id not found: %d", id));
            throw new RuntimeException(String.format("restaurant id not found: %d", id));
        }

        return result.get();
    }


    @Override
    public void add(Dish dish) {
        var restaurant = getRestaurant();
        restaurant.getDishes().add(dish);
        restaurantRepository.save(restaurant);
    }

    @Override
    public Collection<Dish> getAll() {
        return getRestaurant().getDishes();
    }

    @Override
    public void update(Dish dish) {
        var restaurant = getRestaurant();

        var resultDish = restaurant.getDishes()
                .stream()
                .filter(dish1 -> Objects.equals(dish1.getId(), dish.getId()))
                .findFirst();

        if (resultDish.isEmpty()) {
            return;
        }

        resultDish.get().update(dish);
        restaurantRepository.save(restaurant);
    }

    @Override
    public void remove(Dish dish) {
        var restaurant = getRestaurant();
        restaurant.getDishes().removeIf(dish1 -> Objects.equals(dish.getId(), dish1.getId()));
        restaurantRepository.save(restaurant);
    }
}
