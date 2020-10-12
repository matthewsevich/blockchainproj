package by.matusevich.service;

import by.matusevich.repo.TransactionDao;
import by.matusevich.pojo.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService {

    @Autowired
    @Value("#{transactionRepository}")
    TransactionDao<Transaction> transactionRepository;

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);

    public boolean createNewTransaction(String senderId, Transaction transaction) {

        transaction.setWalletId(senderId);
        transaction.setStatus("Pending");
        log.info("creating Tx in TxService {}", transaction);
        if (transactionRepository.find(transaction.getId()) != null) {
            return false;
        }
        transactionRepository.create(transaction);
        return true;
    }

    public Transaction get(String id) {
        return transactionRepository.read(Transaction.class, id);
    }

    public List<Transaction> getAllTxBySenderWalletId(String walletId) {
        return transactionRepository.findAllTxBySenderWalletId(walletId);
    }

    public List<Transaction> getAllTxByReceiverId(String receiverId) {
        return transactionRepository.findAllTxByReceiverId(receiverId);
    }


}
