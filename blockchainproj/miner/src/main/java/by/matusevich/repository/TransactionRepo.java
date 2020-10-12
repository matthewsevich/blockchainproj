package by.matusevich.repository;

import by.matusevich.pojo.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface TransactionRepo extends CrudRepository<Transaction, String> {

    Transaction findFirstByStatus(String status);
}
