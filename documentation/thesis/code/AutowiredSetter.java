public class FooService {
    private FooRepository fooRepository;
    
    @Autowired
    public void setFooRepository (FooRepository fooRepository) {
        this.fooRepository = fooRepository;
    }
}
