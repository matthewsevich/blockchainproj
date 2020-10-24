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

    /*
    validate transaction to be >0 and <=100, to be less than wallet balance
    to have actual receiver(actual receiver's walletid) and checking secret key
     */
    public boolean validateTransaction(Transaction transaction, String walletId, String secretKey) {
        return ((walletService.get(transaction.getReceiverId())) != null)
                && (transaction.getValue() > 0)
                && ((walletService.getBalance(walletId)) > transaction.getValue())
                && (transaction.getValue() <= 100)
                && (createNewTransaction(walletId, transaction))
                && (walletService.get(walletId).getSecretKey().equals(secretKey));
    }

    //get all transactions which were sent out of our wallet(walletId)
    public List<Transaction> getAllTxBySenderWalletId(String walletId) {
        return transactionRepository.findAllTxBySenderWalletId(walletId);
    }

    //get all transactions which are received by this walletId
    public List<Transaction> getAllTxByReceiverId(String receiverId) {
        return transactionRepository.findAllTxByReceiverId(receiverId);
    }

    //get all transactions, 2 previous methods combined
    public List<Transaction> getAllTransactions(String walletId) {
        List<Transaction> allTransactions = getAllTxByReceiverId(walletId);
        log.info("size of list of received transactions {}", allTransactions.size());
        allTransactions.addAll(getAllTxBySenderWalletId(walletId));
        log.info("size of lsit of received transactions plus sended transactions {}", allTransactions.size());

        return allTransactions;
    }

}
