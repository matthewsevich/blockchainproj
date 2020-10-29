package by.matusevich.controller;

import by.matusevich.pojo.AppUser;
import by.matusevich.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/new-registration")
public class RegistrationWithValidationController {
    private static final String REGISTRATION_URL = "/new-registration";
    private static final Logger log = LoggerFactory.getLogger(RegistrationWithValidationController.class);

    @Autowired
    AppUserService appUserService;

    @GetMapping
    public String showRegisterPage(Model model) {
        model.addAttribute("appUser", new AppUser());
        return REGISTRATION_URL;
    }

    @PostMapping
    public String registerNewUser(
            @Valid AppUser appUser,
            BindingResult result) {

        if (result.hasErrors()) {
            log.info("invalid, return to registration");
            return REGISTRATION_URL;
        } else if (appUserService.findByUserName(appUser.getUserName()) == null) {
            log.info("register new validated appUser : {} ", appUser);
            appUserService.createNewUser(appUser);
            return "redirect:home.html";
        }
        return REGISTRATION_URL;
    }
}

