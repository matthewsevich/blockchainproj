package by.matusevich.controller;

import by.matusevich.pojo.Transaction;
import by.matusevich.service.TransactionService;
import by.matusevich.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/wallets/{walletId}/new-transaction.html")
public class NewTransactionController {

    private static final Logger log = LoggerFactory.getLogger(NewTransactionController.class);

    @Autowired
    TransactionService transactionService;
    @Autowired
    WalletService walletService;

    @GetMapping
    public String showNewTransaction(ModelAndView modelAndView,
                                     @PathVariable String walletId) {
        modelAndView.addObject("walletId", walletId);
        modelAndView.setViewName("walletId");
        return "new-transaction";
    }

    @PostMapping
    public String createNewTransaction(
            @PathVariable String walletId,
            @ModelAttribute Transaction transaction,
            Model model) {
        log.info("New transaction: {}", transaction);
        if (((walletService.get(transaction.getReceiverId())) != null)
                && (transaction.getValue() > 0)
                && ((walletService.getBalance(walletId)) >= transaction.getValue())
                && (transaction.getValue() < 100)
                && (transactionService.createNewTransaction(walletId, transaction))) {
            return "redirect:/wallets/{walletId}/transactions.html";
        } else {
            model.addAttribute("errorMessage", "cannot create a new transaction");
            return "error-page";
        }
    }
}
