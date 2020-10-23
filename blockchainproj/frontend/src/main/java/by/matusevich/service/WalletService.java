package by.matusevich.service;

import by.matusevich.pojo.Transaction;
import by.matusevich.pojo.Wallet;
import by.matusevich.repo.WalletDao;
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

    //get balance of wallet, pretty obvious: 2 list of transactions and difference of summs of those value fields
    public long getBalance(String walletId) {
        long outcomeBalance = 0;
        long incomeBalance = 0;

        //sended from wallet
        for (Transaction t : transactionService.getAllTxBySenderWalletId(walletId)) {
            outcomeBalance += t.getValue();
        }
        //received by wallet
        for (Transaction t : transactionService.getAllTxByReceiverId(walletId)) {
            incomeBalance += t.getValue();
        }
        return incomeBalance - outcomeBalance;
    }

    public Wallet get(String walletId) {
        return walletRepository.read(Wallet.class, walletId);
    }

    public List<Wallet> getAll(String ownerId) {

        return walletRepository.findAll(ownerId);
    }

    //it was intended to use with dropdown list, but that list didnt work
    public List<Wallet> getNotMineWallets(String ownerId) {
        return walletRepository.findNotMineWallets(ownerId);
    }

}
