public class FooService {
    private final FooRepository fooRepository;
    
    @Autowired
    public FooService(FooRepository fooRepository) { this.fooRepository = fooRepository; }
}
