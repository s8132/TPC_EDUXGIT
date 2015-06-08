package pl.edu.pjwstk.s8132.store.persistence.dao;


import org.mybatis.spring.support.SqlSessionDaoSupport;
import pl.edu.pjwstk.s8132.domain.Entity;

public abstract class CommonDaoImpl<T extends Entity> extends SqlSessionDaoSupport implements CommonDao<T> {

    @Override
    public T getById(Long id) {
        return (T) getSqlSession().selectOne(prepareStatement("getById"), id);
    }

    @Override
    public Long save(T object) {
        return Long.valueOf(getSqlSession().insert(prepareStatement("insert"), object));
    }

    @Override
    public Long update(T object) {
        return Long.valueOf(getSqlSession().update(prepareStatement("update"), object));
    }

    @Override
    public Long saveOrUpdate(T object) {
        if(object.getId() == null){
            return save(object);
        }else{
            return update(object);
        }
    }

    @Override
    public Long delete(Long id) {
        return Long.valueOf(getSqlSession().delete(prepareStatement("deleteById"), id));
    }

    @Override
    public Long delete(T object) {
        return Long.valueOf(getSqlSession().delete(prepareStatement("delete"), object));
    }

    protected String prepareStatement(String statementId){
        return this.getClass().getName() + "." + statementId;
    }
}
