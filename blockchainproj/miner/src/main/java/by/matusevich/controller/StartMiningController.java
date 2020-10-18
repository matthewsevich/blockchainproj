package by.matusevich.controller;

import by.matusevich.service.Blockchain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartMiningController {
    @Autowired
    Blockchain blockchain;

    private static final Logger log = LoggerFactory.getLogger(StartMiningController.class);

    @RequestMapping("/start/{walletId}")
    public void startMine(@PathVariable String walletId) throws InterruptedException {
        log.info("controller start mining for walletid {}", walletId);
        blockchain.startMine(walletId);
    }
}
