package by.matusevich.service;

import by.matusevich.pojo.Transaction;
import by.matusevich.repository.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MiningTransactionService {

    @Autowired
    TransactionRepo transactionRepo;

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
