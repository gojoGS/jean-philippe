public interface CrudListener<T> extends Serializable {
    Collection<T> findAll();
    T add(T var1);
    T update(T var1);
    void delete(T var1);
}
