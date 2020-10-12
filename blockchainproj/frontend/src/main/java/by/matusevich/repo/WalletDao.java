package by.matusevich.repo;

import java.io.Serializable;
import java.util.List;

public interface WalletDao<T> {

    void create(T t);

    void update(T t);

    T read(Class clazz, Serializable id);

    void delete(T t);

    T find(String id);

    List<T> findAll(String searchStr);

    List<T> findNotMineWallets(String ownerId);
}
