package by.matusevich.repo;

import java.util.List;

public interface WalletDao<T> {

    void create(T t);

    T find(String id);

    List<T> findAll(String searchStr);

    List<T> findNotMineWallets(String ownerId);
}
