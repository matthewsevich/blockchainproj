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
    @Autowired
    WalletService walletService;

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

    public boolean validateTransaction(Transaction transaction, String walletId) {
        return ((walletService.get(transaction.getReceiverId())) == null)
                || (transaction.getValue() <= 0)
                || ((walletService.getBalance(walletId)) < transaction.getValue())
                || (transaction.getValue() >= 100)
                || (!createNewTransaction(walletId, transaction));
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

    public List<Transaction> getAllTransactions(String walletId) {
        List<Transaction> allTransactions = getAllTxByReceiverId(walletId);
        log.info("size {}", allTransactions.size());
        allTransactions.addAll(getAllTxBySenderWalletId(walletId));
        log.info("size {}", allTransactions.size());

        return allTransactions;
    }

}
