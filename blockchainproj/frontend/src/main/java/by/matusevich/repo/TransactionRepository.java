package by.matusevich.repo;

import by.matusevich.pojo.Transaction;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository("transactionRepository")
public class TransactionRepository implements TransactionDao<Transaction> {

    @Autowired
    SessionFactory sessionFactory;

    @Override
    @Transactional
    public void create(Transaction transaction) {
        sessionFactory
                .getCurrentSession()
                .saveOrUpdate(transaction);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, readOnly = true)
    public Transaction find(String id) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.id=:id", Transaction.class)
                .setParameter("id", id)
                .list()
                .stream()
                .findFirst()
                .orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllTxBySenderWalletId(String walletId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.walletId like :walletId", Transaction.class)
                .setParameter("walletId", walletId)
                .list();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Transaction> findAllTxByReceiverId(String receiverId) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Transaction t where t.receiverId like :receiverId", Transaction.class)
                .setParameter("receiverId", receiverId)
                .list();
    }
}