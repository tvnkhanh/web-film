package ptit.wibulord.webfilm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/management")
public class ManagementController {

    @GetMapping("")
    public String managementPage(){
        return "/management/management";
    }
    @GetMapping("/filmCategory")
    public String management_CategoryPage(){
        return "/management/management_category";
    }
}
