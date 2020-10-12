package by.matusevich.repo;

import java.io.Serializable;
import java.util.List;

public interface AppUserDao<T> {

    void create(T t);

    void update(T t);

    T read(Class clazz, Serializable id);

    T find(String id);

    List<T> findAll(String searchStr);
}
