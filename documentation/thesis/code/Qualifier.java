@Component("fooComponent")
public class FooComponent implements SomeComponent {
   // ..
}

public class FooService {
    @Autowired
    @Qualifier("fooComponent")
    private SomeComponent component;
}
