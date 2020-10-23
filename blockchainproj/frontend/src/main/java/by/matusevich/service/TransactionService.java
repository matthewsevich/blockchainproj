package by.matusevich.service;

import by.matusevich.pojo.Transaction;
import by.matusevich.repo.TransactionDao;
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

    /*
        validate transaction to be positive less than 100 and wallet balance
     */
    public boolean validateTransaction(Transaction transaction, String walletId) {
        return ((walletService.get(transaction.getReceiverId())) != null)
                || (transaction.getValue() <= 0)
                || ((walletService.getBalance(walletId)) < transaction.getValue())
                || (transaction.getValue() >= 100)
                || (!createNewTransaction(walletId, transaction));
    }

    //get all transactions which are sended from this wallet
    public List<Transaction> getAllTxBySenderWalletId(String walletId) {
        return transactionRepository.findAllTxBySenderWalletId(walletId);
    }

    //get all transactions which are received by this wallet
    public List<Transaction> getAllTxByReceiverId(String receiverId) {
        return transactionRepository.findAllTxByReceiverId(receiverId);
    }


    //get all transactions which are from|to this wallet
    public List<Transaction> getAllTransactions(String walletId) {
        List<Transaction> allTransactions = getAllTxByReceiverId(walletId);
        log.info("size {}", allTransactions.size());
        allTransactions.addAll(getAllTxBySenderWalletId(walletId));
        log.info("size {}", allTransactions.size());

        return allTransactions;
    }

}
