package ptit.wibulord.webfilm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ptit.wibulord.webfilm.model.*;
import ptit.wibulord.webfilm.service.AccountService;
import ptit.wibulord.webfilm.service.MailService;
import ptit.wibulord.webfilm.service.UserService;

import java.util.Random;

@Controller
@RequestMapping("/")
@SessionAttributes("message")
public class RegisterController {
    private int codeConfirm=0;
    private User user;
    private Account account;
    @Autowired
    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
    @GetMapping("/")
    public String showLoginPage(){
        return "login";
    }

    @PostMapping("login")
    public String login(@RequestParam("username")String username,
                        @RequestParam("password")String password,ModelMap model,@ModelAttribute("user")User user){
        Account account = accountService.findAccountByUsername(username);

        if(account==null){
            model.addAttribute("message","Tài khoản không tồn tại");
            return "login";
        }
        if(!account.getPassword().equals(password)){
            model.addAttribute("message","Tài khoản hoặc mật khẩu không đúng");
            return "login";
        }
        if(!account.isStatus()){
            model.addAttribute("message","Tài khoản của bạn đã bị khóa");
            return "login";
        }

//        model.remove("message");
        user = account.getUser();
        model.addAttribute("user",user);
        return "redirect:/home";
    }
    @GetMapping("register")
    public String showRegisterPage(ModelMap model){

        return "register";
    }
    @PostMapping("register")
    public String register(@RequestParam("username") String userName,
                           @RequestParam ("password") String password,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("gender")int gender,ModelMap model){

        if(accountService.findAccountByUsername(userName)!=null) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại");
            return "register";
        }

        if (userService.findUserByEmail(email)!=null){
            model.addAttribute("errorMessage", "Email đã được sử dụng");

            return "register";
        }
        else{
            user = new User();
            user.setFullName(name);
            user.setEmail(email);
            user.setImgPath("https://i.pinimg.com/474x/a1/8d/56/a18d56104193738a5ac7b9bfb43ba09d.jpg");
            if(gender==0){
                user.setGender("Nam");
            }else if (gender==1){
                user.setGender("Nữ");
            }else {
                user.setGender("Không biết");
            }
            account=new Account();
            account.setUsername(userName);
            account.setStatus(true);
            account.setPassword(password);
            Role role = new Role();
            role.setIdRole(1);
            role.setRoleName("Member");
            account.setRole(role);
            user.setAccount(account);
            user.setFavoriteList(new FavoriteList());
            user.setWatchList(new WatchList());
            codeConfirm = new Random().nextInt(999999) + 1;
            mailService.sendSimpleMail(new EmailDetails(email,Integer.toString(codeConfirm),"Thư xác nhận"));
            return "redirect:/confirm";
        }
    }
    @GetMapping("confirm")
    public String confirmRegister(){
        codeConfirm = new Random().nextInt(999999) + 1;
        mailService.sendSimpleMail(new EmailDetails(user.getEmail(),Integer.toString(codeConfirm),"Thư xác nhận"));
        return "ConfirmRegister";
    }

    @PostMapping("confirm")
    public String confirmRegister(ModelMap model, @RequestParam("code") int code){
        if(codeConfirm==code){
            model.remove("message");
            return "login";
        }
        model.addAttribute("message","Mã nhập vào không khớp.");
        return "redirect:/confirm";
    }
}
