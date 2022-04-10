@Component("fooComponent")
public class FooComponent implements SomeComponent {
   // ..
}

public class FooService {
    @Autowired
    private SomeComponent fooComponent;
}
