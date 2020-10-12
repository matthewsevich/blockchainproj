package by.matusevich.repo;

import by.matusevich.pojo.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository("appUserRepository")
public class AppUserRepository implements AppUserDao<AppUser> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void create(AppUser appUser) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(appUser);
    }

    @Override
    public void update(AppUser appUser) {
        create(appUser);
    }

    @Override
    public AppUser read(Class clazz, Serializable id) {
        return sessionFactory
                .getCurrentSession()
                .get(AppUser.class, id);
    }



    @Override
    public AppUser find(String userName) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from AppUser u where u.userName=:username", AppUser.class)
                .setParameter("username", userName)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<AppUser> findAll(String searchStr) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from AppUser", AppUser.class)
                .list();
    }
}
