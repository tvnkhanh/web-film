package ptit.wibulord.webfilm.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import ptit.wibulord.webfilm.model.*;
import ptit.wibulord.webfilm.service.*;

import java.util.Date;
import java.util.Random;

@ComponentScan
@Controller
public class HomeController {
    @Autowired
    AccountService accountService;
    @Autowired
    FavoriteListService favoriteListService;
    @Autowired
    WatchListService watchListService;
    @Autowired
    EpisodeService episodeService;
    @Autowired
    FilmService filmService;

    @RequestMapping("/home")
    public String index(ModelMap model) {
        model.addAttribute("user",user);
        model.addAttribute("newestEp", episodeService.getNewestList());
        model.addAttribute("tierList", episodeService.getTierList());
        model.addAttribute("movie", filmService.getMovie());
        model.addAttribute("randomFilm", filmService.getRandom());
        return "index";
    }

    @RequestMapping("/bxh")
    public String loadBXH(ModelMap model) {
        model.addAttribute("user",user);
        return "bxh";
    }

    @RequestMapping("/profile")
    public String loadProfile(ModelMap model) {
        model.addAttribute("user",user);
        return "profile";
    }

    @RequestMapping("/watch")
    public String loadWatch(ModelMap model, @RequestParam(value = "id") int id, @RequestParam(value = "ep") int ep) {
        model.addAttribute("user", user);
        model.addAttribute("film", filmService.getFilmById(id));
        model.addAttribute("currentEp", episodeService.getEpById(ep));

        return "watch";
    }

    @RequestMapping("/search")
    public String search(ModelMap model, @RequestParam(value = "keyword") String keyword) {
        model.addAttribute("user", user);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filmList", filmService.searchFilm(keyword));

        return "search";
    }

    ///login-register
    private int codeConfirm = 0;
    public static User user;
    private Account account;
    private FavoriteList favoriteList;
    private WatchList watchList;
    private String message;
//    @Autowired
//    AccountService accountService;
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;

    @GetMapping("/")
    public String showLoginPage() {
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, RedirectAttributes redirect) {
        Account account = accountService.findAccountByUsername(username);
        if (account == null ||!account.isStatus()) {
            redirect.addFlashAttribute("message","Tài khoản không tồn tại hoặc bị khóa!");
            return "redirect:/";
        }
        if (!account.getPassword().equals(password)) {
            redirect.addFlashAttribute("message","Sai tài khoản hoặc mật khẩu!");
            return "redirect:/";
        }
        user = account.getUser();
        return "redirect:/home";
    }

    @GetMapping("/register")
    public String showRegisterPage(ModelMap model) {
        return "/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String userName,
                           @RequestParam("password") String password,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("gender") int gender, ModelMap model) {

        if (accountService.findAccountByUsername(userName) != null) {
            model.addAttribute("errorMessage", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        if (userService.findUserByEmail(email) != null) {
            model.addAttribute("errorMessage", "Email đã được sử dụng!");

            return "register";
        } else {
            user = new User();
            user.setFullName(name);
            user.setEmail(email);
            user.setImgPath("https://i.pinimg.com/474x/a1/8d/56/a18d56104193738a5ac7b9bfb43ba09d.jpg");
            if (gender == 0) {
                user.setGender("Nam");
            } else if (gender == 1) {
                user.setGender("Nữ");
            } else {
                user.setGender("Không biết");
            }
            account = new Account();
            account.setUsername(userName);
            account.setStatus(true);
            account.setCreateDate(new java.util.Date());
            account.setPassword(password);
            Role role = new Role();
            role.setIdRole(1);
            role.setRoleName("Member");
            account.setRole(role);
            user.setAccount(account);
            return "redirect:/confirm";
        }
    }

    @GetMapping("/confirm")
    public String confirmRegister() {
        codeConfirm = new Random().nextInt(900000) + 1;
        mailService.sendSimpleMail(new EmailDetails(user.getEmail(), Integer.toString(codeConfirm), "Thư xác nhận"));
        return "confirmRegister";
    }

    @PostMapping("/confirm")
    public String confirmRegister(ModelMap model, @RequestParam("code") int code,RedirectAttributes redirect) {
        if (codeConfirm == code) {
            userService.addUser(user);
            int maxId = userService.findIDMax();
            User temp = userService.findUserById(maxId);
            favoriteListService.addFavoriteList(new FavoriteList(temp));
            watchListService.addWatchList(new WatchList(temp));
            redirect.addFlashAttribute("message","Tạo tài khoản thành công!");
            return "redirect:/";
        }
        redirect.addFlashAttribute("message","Mã nhập vào không khớp!");
        return "redirect:/confirm";
    }

}