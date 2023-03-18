package ptit.wibulord.webfilm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class HomeController {
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
}
