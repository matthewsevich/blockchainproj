package by.matusevich.service;

import by.matusevich.pojo.Transaction;
import by.matusevich.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiningTransactionService {

    @Autowired
    TransactionRepo transactionRepo;
/*
methods for Transactions in "MINING MODULE"
    - createGenesisTransaction - creates first transaction if blockchain is empty
    - save
    - count to count amount of transactions
    - getPendingTransaction - method to find transaction which is not writed to block yet
 */
    public Transaction createGenesisTransaction(String walletId) {
        return new Transaction(null, null, walletId, 5000, "genesis");
    }

    public void saveTransaction(Transaction transaction) {
        transactionRepo.save(transaction);
    }

    public long count() {
        return transactionRepo.count();
    }

    public Transaction getPendingTransaction() {
        return transactionRepo.findFirstByStatus("Pending");
    }
}
