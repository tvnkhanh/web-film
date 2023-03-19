package ptit.wibulord.webfilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ptit.wibulord.webfilm.model.Account;
import ptit.wibulord.webfilm.service.AccountService;

@Controller
public class HomeController {
    @Autowired
    AccountService accountService;
    @RequestMapping("/")
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
    @RequestMapping("/test")
    public String test(ModelMap model, Account account){
        model.addAttribute("account", accountService.getAccounts());
        return"test";
    }
}
