package by.matusevich.repo;

import by.matusevich.pojo.Wallet;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository("walletRepository")
public class WalletRepository implements WalletDao<Wallet> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Wallet wallet) {
        sessionFactory.getCurrentSession()
                .saveOrUpdate(wallet);
    }

    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Wallet find(String walletId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.walletId=:walletid", Wallet.class)
                .setParameter("walletid", walletId)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Wallet> findAll(String searchStr) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.ownerId =:ownerId", Wallet.class)
                .setParameter("ownerId", searchStr)
                .list();
    }

    @Transactional(readOnly = true)
    public List<Wallet> findNotMineWallets(String ownerId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Wallet w where w.ownerId not like :ownerId", Wallet.class)
                .setParameter("ownerId", "%" + ownerId + "%")
                .list();
    }

}
