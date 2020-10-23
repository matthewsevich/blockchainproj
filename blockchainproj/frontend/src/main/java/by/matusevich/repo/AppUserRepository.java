package by.matusevich.repo;

import by.matusevich.pojo.AppUser;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

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
}
