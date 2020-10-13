package by.matusevich.controller;

import by.matusevich.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class BalanceController {
    private static final Logger log = LoggerFactory.getLogger(BalanceController.class);

    @Autowired
    WalletService walletService;

    @GetMapping("/wallets/{walletId}/balance.html")
    public ModelAndView walletBalance(
            ModelAndView modelAndView,
            @PathVariable String walletId) {

        log.info("show wallet {} balance {}", walletId, walletService.getBalance(walletId));

        modelAndView.addObject("balance", walletService.getBalance(walletId));
        modelAndView.setViewName("balance");

        return modelAndView;
    }
}
