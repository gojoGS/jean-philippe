@AllArgsConstructor
public class EntityServiceAdapter<T> implements CrudListener<T> {
    private final EntityService<T> service;

    @Override
    public Collection<T> findAll() { return service.getAll(); }

    @Override
    public T add(T t) {
        service.add(t);
        return t;
    }

    @Override
    public T update(T t) {
        service.update(t);
        return t;
    }

    @Override
    public void delete(T t) { service.remove(t); }
}
