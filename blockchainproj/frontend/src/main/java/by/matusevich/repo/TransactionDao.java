package by.matusevich.repo;

import java.io.Serializable;
import java.util.List;

public interface TransactionDao<T> {

    void create(T t);

    T read(Class clazz, Serializable id);

    T find(String id);

    List<T> findAllTxBySenderWalletId(String walletId);

    List<T> findAllTxByReceiverId(String receiverId);
}


