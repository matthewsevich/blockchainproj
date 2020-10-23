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

    public boolean startMine(String walletId) throws InterruptedException {
        log.info("start mining, walletId {}", walletId);
        boolean flag = true;//flag - is mining running atm?

        /*
        checking if blockchain is empty
        creating genesis transaction - saving transaction, then put it into genesis block and saving the block
         */
        if ((miningBlockService.count() < 1) && (miningTransactionService.count() < 1)) {
            Transaction genesisTransaction = miningTransactionService.createGenesisTransaction(walletId);
            miningTransactionService.saveTransaction(genesisTransaction);

            Block genesisBlock = miningBlockService.createGenesisBlock(genesisTransaction);
            miningBlockService.saveBlock(genesisBlock);

        }
        while (flag) {
            Thread.sleep(5000);//to slow down
            Transaction pendingTransaction = miningTransactionService.getPendingTransaction();//getting not accepted(new transaction)
            log.info("pending Tx {}", pendingTransaction);
            if (pendingTransaction != null) {
                //creating block and saving it
                miningBlockService.saveBlock(
                        miningBlockService.createBlock(pendingTransaction));

                flag = utils.isBlockchainValid();// if blockchain is corrupted or broken, flag set to false and mining will stop
                log.info("blockchain is valid {}", flag);
            }
        }
        return flag;
    }
}
