package by.matusevich.controller;

import by.matusevich.pojo.Wallet;
import by.matusevich.service.AppUserService;
import by.matusevich.service.WalletService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class WalletListController {

    private static final Logger log = LoggerFactory.getLogger(WalletListController.class);

    @Autowired
    WalletService walletService;

    @Autowired
    AppUserService appUserService;

    @GetMapping("/wallets.html")
    public ModelAndView walletList(ModelAndView modelAndView) {
        String ownerId = appUserService.findByUserName(AppUserService.getAppUserUserName()).getId();
        List<Wallet> wallets = walletService.getAll(ownerId);

        modelAndView.setViewName("wallets");
        modelAndView.addObject("wallets", wallets);
        return modelAndView;
    }

    @PostMapping("localhost:8082/start/${wallet.walletId}")
    public String startMine(@PathVariable String walletId) {
        return "wallets";
    }
}
