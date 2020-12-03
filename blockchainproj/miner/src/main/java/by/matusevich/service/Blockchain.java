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
    BlockService blockService;
    @Autowired
    MiningBlock miningBlock;
    @Autowired
    MiningTransactionService miningTransactionService;
    @Autowired
    Utils utils;

    private static final Logger log = LoggerFactory.getLogger(Blockchain.class);

    private final int difficulty = 5;

    public boolean startMine(String walletId) throws InterruptedException {
        log.info("start mining, walletId {}", walletId);
        boolean flag = true;//flag - is mining running atm?
        /*
        checking if blockchain is empty
        creating genesis transaction - saving transaction, then put it into genesis block and saving the block
         */
        if ((blockService.count() < 1) && (miningTransactionService.count() < 1)) {
            Transaction genesisTransaction = miningTransactionService.createGenesisTransaction(walletId);
            miningTransactionService.saveTransaction(genesisTransaction);

            Block genesisBlock = blockService.createGenesisBlock(genesisTransaction);
            miningBlock.mineBlock(genesisBlock, difficulty);

            blockService.saveBlock(genesisBlock);

        }
        while (flag) {
            Thread.sleep(5000);//to slow down
            Transaction pendingTransaction = miningTransactionService.getPendingTransaction();
            //getting not accepted(new transaction)
            log.info("pending Tx {}", pendingTransaction);
            flag = utils.isBlockchainValid();// if blockchain is corrupted or broken, flag set to false and mining will stop
            if (!flag)
                return flag;
            if (pendingTransaction != null) {
                //creating block without nonce and hash, then we'll mine it and saving it
                blockService.saveBlock(
                        miningBlock.mineBlock(
                                blockService.createBlock(pendingTransaction),
                                difficulty));

                flag = utils.isBlockchainValid();// if blockchain is corrupted or broken, flag set to false and mining will stop
                log.info("blockchain is valid {}", flag);
            }
        }
        return flag;
    }
}
