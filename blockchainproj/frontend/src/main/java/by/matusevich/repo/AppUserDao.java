package by.matusevich.repo;

public interface AppUserDao<T> {

    void create(T t);

    T find(String id);

}
