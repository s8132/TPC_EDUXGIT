package pl.edu.pjwstk.s8132.store.persistence.dao;

public interface CommonDao<T> {

    public T getById(Long id);
    public Long save(T object);
    public Long update(T object);
    public Long saveOrUpdate(T object);
    public Long delete(T object);
    public Long delete(Long id);
}
