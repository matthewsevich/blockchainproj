package by.matusevich.controller;

import by.matusevich.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class TransactionListController {

    private static final Logger log = LoggerFactory.getLogger(TransactionListController.class);

    @Autowired
    TransactionService transactionService;

    @GetMapping("/wallets/{walletId}/transactions.html")
    public ModelAndView transactionList(
            ModelAndView modelAndView,
            @PathVariable String walletId
    ) {
        log.info("list transactions by: {}", walletId);
        modelAndView.addObject("transactions", transactionService.getAllTransactions(walletId));
        modelAndView.setViewName("transactions");

        return modelAndView;
    }
}
