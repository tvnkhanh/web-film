package ptit.wibulord.webfilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ptit.wibulord.webfilm.model.Account;
import ptit.wibulord.webfilm.service.AccountService;

@Controller
public class HomeController {
    @Autowired
    AccountService accountService;

    @RequestMapping("/home")
    public String index() {
        return "index";
    }

    @RequestMapping("/bxh")
    public String loadBXH() {
        return "bxh";
    }

    @RequestMapping("/profile")
    public String loadProfile() {
        return "profile";
    }
}

