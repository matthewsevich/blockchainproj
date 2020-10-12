package by.matusevich.controller;

import by.matusevich.pojo.Wallet;
import by.matusevich.service.AppUserService;
import by.matusevich.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/new-wallet.html")
public class NewWalletController {

    private static final Logger log = LoggerFactory.getLogger(NewWalletController.class);

    @Autowired
    WalletService walletService;

    @Autowired
    AppUserService appUserService;

    @GetMapping
    public String showNewWallet() {
        return "new-wallet";
    }

    @PostMapping
    public String createNewWallet(
            @ModelAttribute Wallet wallet,
            Model model
    ) {
        log.info("New Wallet: {}", wallet);
        wallet.setOwnerId(
                appUserService.findByUserName(
                        AppUserService.getAppUserUserName())
                        .getId());
        if (walletService.createNewWallet(wallet)) {
            return "redirect:wallets.html";
        } else {
            model.addAttribute("errorMessage", "cannot create a new wallet");
            return "error-page";
        }
    }
}
