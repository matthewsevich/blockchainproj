package by.matusevich.controller;

import by.matusevich.service.Blockchain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StartMiningController {

    @Autowired
    Blockchain blockchain;

    private static final Logger log = LoggerFactory.getLogger(StartMiningController.class);

    @RequestMapping("/start/{walletId}")
    public String startMine(@PathVariable String walletId) throws InterruptedException {
        log.info("controller start mining for walletId {}", walletId);
        if (blockchain.startMine(walletId)) {
            return "Mining is started";
        } else {
            log.error("mining stopped");
            return "Mining doesn't started";
        }
    }
}
