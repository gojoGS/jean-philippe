@Component
public class DetailsServiceFactoryImpl implements DetailsServiceFactory {
    @Autowired
    private RestaurantRepository restaurantRepository;

    @Override
    public DetailsService get(long restaurantId) { return new DetailsServiceImpl(restaurantId); }

    @AllArgsConstructor
    private class DetailsServiceImpl implements DetailsService {
        private long id;
        
        // ...

        @Override
        public void setName(String name) {
            // ..
        }
        
        @Override
        public void setDescription(String description) { 
            // ..
        }
    }
}
