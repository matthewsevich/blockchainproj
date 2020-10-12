package by.matusevich.controller;

import by.matusevich.pojo.Transaction;
import by.matusevich.service.TransactionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

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
        List<Transaction> transactions = transactionService.getAllTxBySenderWalletId(walletId);
        List<Transaction> transactions2 = transactionService.getAllTxByReceiverId(walletId);
        transactions.addAll(transactions2);
        modelAndView.addObject("transactions", transactions);
        modelAndView.setViewName("transactions");

        return modelAndView;
    }
}
