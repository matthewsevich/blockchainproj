package by.matusevich.service;

import by.matusevich.pojo.Transaction;
import by.matusevich.repo.WalletDao;
import by.matusevich.pojo.Wallet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletService {

    private static final Logger log = LoggerFactory.getLogger(WalletService.class);

    @Autowired
    @Value("#{walletRepository}")
    WalletDao<Wallet> walletRepository;
    @Autowired
    TransactionService transactionService;

    @Autowired
    AppUserService appUserService;

    public boolean createNewWallet(Wallet wallet) {
        wallet.setOwnerId(
                appUserService.findByUserName(
                        AppUserService.getAppUserUserName())
                        .getId());
        log.info("creating new wallet: {}", wallet);
        if (walletRepository.find(wallet.getWalletId()) != null) {
            return false;
        }
        walletRepository.create(wallet);
        return true;
    }

    public long getBalance(String walletId) {
        int outcomeBalance = 0;
        int incomeBalance = 0;

        for (Transaction t : transactionService.getAllTxBySenderWalletId(walletId)) {
            outcomeBalance += t.getValue();
        }
        for (Transaction t : transactionService.getAllTxByReceiverId(walletId)) {
            incomeBalance += t.getValue();
        }
        return (long) incomeBalance - outcomeBalance;
    }

    public Wallet get(String walletId) {
        return walletRepository.read(Wallet.class, walletId);
    }

    public List<Wallet> getAll(String ownerId) {

        return walletRepository.findAll(ownerId);
    }

    public List<Wallet> getNotMineWallets(String ownerId) {
        return walletRepository.findNotMineWallets(ownerId);
    }

}
