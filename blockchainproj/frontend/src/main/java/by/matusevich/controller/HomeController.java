package by.matusevich.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/home.html")
public class HomeController {

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @GetMapping
    public ModelAndView homepage(ModelAndView modelAndView) {
        log.info("call homepage");

        modelAndView.addObject("homepage", "value of homepage");
        modelAndView.setViewName("home");
        return modelAndView;
    }
}
