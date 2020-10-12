package by.matusevich.controller;

import by.matusevich.pojo.Block;
import by.matusevich.pojo.Transaction;
import by.matusevich.service.MiningBlockService;
import by.matusevich.service.MiningTransactionService;
import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartMiningController {
    @Autowired
    MiningBlockService miningBlockService;
    @Autowired
    MiningTransactionService miningTransactionService;

    private static final Logger log = LoggerFactory.getLogger(StartMiningController.class);

    @SneakyThrows
    @RequestMapping("/start/{walletId}")
    public void startMine(@PathVariable String walletId) {
        log.info("start mining, walletId {}", walletId);


        if ((miningBlockService.count() < 1) && (miningTransactionService.count() < 1)) {
            Transaction genesisTransaction = miningTransactionService.createGenesisTransaction(walletId);
            miningTransactionService.saveTransaction(genesisTransaction);

            Block genesisBlock = miningBlockService.createGenesisBlock(genesisTransaction);
            miningBlockService.saveBlock(genesisBlock);

        }
        while (true) {
            Thread.sleep(5000);
            Transaction pendingTransaction = miningTransactionService.getPendingTransaction();
            log.info("pending Tx {}", pendingTransaction);
            if (pendingTransaction != null) {
                miningBlockService.saveBlock(
                        miningBlockService.createBlock(pendingTransaction));
            }
        }
    }

}
