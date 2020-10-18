package by.matusevich.service;

import by.matusevich.pojo.Block;
import by.matusevich.pojo.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Blockchain {

    @Autowired
    MiningBlockService miningBlockService;
    @Autowired
    MiningTransactionService miningTransactionService;
    @Autowired
    Utils utils;

    private static final Logger log = LoggerFactory.getLogger(Blockchain.class);

    private boolean flag = false;

    public void startMine(String walletId) throws InterruptedException {
        log.info("start mining, walletId {}", walletId);
        flag = true;

        if ((miningBlockService.count() < 1) && (miningTransactionService.count() < 1)) {
            Transaction genesisTransaction = miningTransactionService.createGenesisTransaction(walletId);
            miningTransactionService.saveTransaction(genesisTransaction);

            Block genesisBlock = miningBlockService.createGenesisBlock(genesisTransaction);
            miningBlockService.saveBlock(genesisBlock);

        }
        while (flag) {
            Thread.sleep(5000);
            Transaction pendingTransaction = miningTransactionService.getPendingTransaction();
            log.info("pending Tx {}", pendingTransaction);
            if (pendingTransaction != null) {
                miningBlockService.saveBlock(
                        miningBlockService.createBlock(pendingTransaction));

                flag = utils.isBlockchainValid();
                log.info("blockchain is valid {}", flag);

            }
        }
    }
}
