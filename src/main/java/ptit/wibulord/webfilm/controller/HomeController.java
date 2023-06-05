package ptit.wibulord.webfilm.controller;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ptit.wibulord.webfilm.model.*;
import ptit.wibulord.webfilm.service.*;

import java.util.Random;

@ComponentScan
@Controller
public class HomeController {
    private int codeConfirm = 0;
    public static User user;
    private Account account;
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
    @Autowired
    CategoryService categoryService;
    @Autowired
    UserService userService;
    @Autowired
    MailService mailService;
    @Autowired
    PremiumService premiumService;
    @Autowired
    DetailPurchaseService detailPurchaseService;

    @Autowired
    BuyFilmService buyFilmService;

    @RequestMapping("/home")
    public String index(ModelMap model) {
        model.addAttribute("user",user);
        model.addAttribute("newestEp", episodeService.getNewestList());
        model.addAttribute("tierList", episodeService.getTierList());
        model.addAttribute("movie", filmService.getMovie());
        model.addAttribute("randomFilm", filmService.getRandom());
        return "index";
    }

//
    @RequestMapping("/anime/{page}")
    public String loadAnimeByPage(ModelMap model ,@PathVariable("page")String page) {
        int numberPage = Integer.parseInt(page.substring(5));
        model.addAttribute("user",user);
        model.addAttribute("filmList",filmService.getFilmByPage(numberPage));
        model.addAttribute("categoryList", categoryService.getCategoryList());
        model.addAttribute("pages",filmService.getPage());
        model.addAttribute("currentPage",numberPage);
        return "anime";
    }
    @RequestMapping("/anime/filter")
    public String loadAnimeByType(
            @RequestParam("type") String type,
            @RequestParam("page")int page,
            ModelMap model) {
//        int numberPage = Integer.parseInt(page.substring(5));
        model.addAttribute("user",user);
        model.addAttribute("filmList",filmService.getFilmPageByType(page,type));
        model.addAttribute("categoryList", categoryService.getCategoryList());
        model.addAttribute("pages",filmService.getPageByType(type));
        model.addAttribute("currentPage",page);
        return "anime";
    }
    @RequestMapping("/anime/{category}/{page}")
    public String loadAnimeByCategory(
            @PathVariable("category") String category,
            @PathVariable("page")String page,
            ModelMap model) {
        int numberPage = Integer.parseInt(page.substring(5));
        model.addAttribute("user",user);
        model.addAttribute("filmList",filmService.getFilmPageByCategory(numberPage, category));
        model.addAttribute("categoryList", categoryService.getCategoryList());
        model.addAttribute("pages",filmService.getPageByCategory(category));
        model.addAttribute("currentPage",numberPage);
        return "anime";
    }
    @RequestMapping("/movie/{page}")
    public String loadMovieByPage(ModelMap model ,@PathVariable("page")String page) {
        int numberPage = Integer.parseInt(page.substring(5));
        model.addAttribute("user",user);
        model.addAttribute("filmList",filmService.getFilmPageByType(numberPage,"MOVIE"));
        model.addAttribute("pages",filmService.getPageByType("MOVIE"));
        model.addAttribute("currentPage",numberPage);
        return "movie";
    }

    @RequestMapping("/bxh")
    public String loadBXH(ModelMap model) {
        model.addAttribute("user",user);
        model.addAttribute("list",filmService.get24FilmTopTier() );
        return "bxh";
    }

    @RequestMapping("/profile")
    public String loadProfile(ModelMap model) {
        model.addAttribute("user",user);
        return "profile";
    }

    @GetMapping("/edit-info/{id}")
    public String showEdit(@PathVariable(value = "id") int id, ModelMap model){
        model.addAttribute("user", userService.findUserById(id));
        System.out.println(userService.findUserById(id).getGender());
        return "Edit_info";
    }

    @PostMapping("/edit-info/{id}")
    public String saveInfo(@PathVariable(value = "id") int id, RedirectAttributes redirect,
                           @RequestParam("name") String name,
                           @RequestParam("email") String email,
                           @RequestParam("img") String imgPath,
                           @RequestParam("gender") int gender){
        User tmpUser = userService.findUserByEmail(email);
        if(tmpUser!=null && !tmpUser.getEmail().equals(user.getEmail())){
            redirect.addFlashAttribute("message", "Email đã được sử dụng! Vui lòng chọn email khác.");
        }else {
            tmpUser = userService.findUserById(id);
            tmpUser.setEmail(email);
            tmpUser.setFullName(name);
            tmpUser.setImgPath(imgPath);
            if(gender == 0 ) {
                tmpUser.setGender("Nam");
            }else if(gender == 1){
                tmpUser.setGender("Nữ");
            }else {
                tmpUser.setGender("Không nói");
            }

            try{
                userService.addUser(tmpUser);
                redirect.addFlashAttribute("message","Cập nhật thông tin thành công !");
            }catch (Exception e){
                redirect.addFlashAttribute("message", "Cập nhật thông tin thất bại.");
            }
            user = tmpUser;
        }
        return "redirect:/edit-info/" + id;
    }
    @GetMapping("/change-password")
    public String showChangePassPage( ModelMap model){
        model.addAttribute("user", user);
        return "change_pass";
    }

    @PostMapping("/change-password")
    public String savePass(RedirectAttributes redirect,
                           @RequestParam("oldPass") String oldPass,
                           @RequestParam("newPass") String newPass){
        if(!user.getAccount().getPassword().equals(oldPass)){
            redirect.addFlashAttribute("message", "Mật khẩu hiện tại không đúng! Vui lòng nhập lại.");
        }else {
            try{
                user.getAccount().setPassword(newPass);
                userService.addUser(user);
                redirect.addFlashAttribute("message","Thay đổi mật khẩu thành công!");
            }catch (Exception e){
                redirect.addFlashAttribute("message", "Thay đổi mật khẩu thất bại. Vui lòng thử lại.");
            }
        }
        return "redirect:/change-password";
    }

    @GetMapping("/favorite")
    public String loadFavorite(ModelMap model){
        model.addAttribute("user", user);
        model.addAttribute("list", favoriteListService.findByID(user.getFavoriteList().getIdFavoriteList()).getFilms());
        return "favorite_film";
    }
    @GetMapping("/favorite/remove/{id}")
    public String removeFavorite(ModelMap model,RedirectAttributes redirect,
                                 @PathVariable(value = "id") int id){
        try{
            FavoriteList favoriteList = favoriteListService.findByID(user.getFavoriteList().getIdFavoriteList());
            for(Film film :favoriteList.getFilms()){
                if(film.getFilmID() == id){
                    favoriteList.getFilms().remove(film);
                    break;
                }
            }
            favoriteListService.addFavoriteList(favoriteList);
            user = userService.findUserById(user.getIdUser());
            redirect.addFlashAttribute("message", "Bỏ yêu thích phim thành công!");
        }catch (Exception e){
            redirect.addFlashAttribute("message", "Bỏ yêu thích phim không thành công. Vui lòng thử lại.");
        }
        return "redirect:/favorite";
    }
    @GetMapping("/follow")
    public String loadFilmFollowList(ModelMap model){
        model.addAttribute("user", user);
        model.addAttribute("list",watchListService.findByID(user.getWatchList().getIdWatchList()).getFilms());
        return "film_followed";
    }
    @GetMapping("/follow/remove/{id}")
    public String removeFollow(ModelMap model,RedirectAttributes redirect,
                                 @PathVariable(value = "id") int id){
        try{
            WatchList watchList = watchListService.findByID(user.getWatchList().getIdWatchList());
            for(Film film : watchList.getFilms()){
                if(film.getFilmID() == id){
                    watchList.getFilms().remove(film);
                    break;
                }
            }
            watchListService.addWatchList(watchList);
            user = userService.findUserById(user.getIdUser());
            redirect.addFlashAttribute("message", "Bỏ theo dõi phim thành công!");
        }catch (Exception e){
            redirect.addFlashAttribute("message", "Bỏ theo dõi phim không thành công. Vui lòng thử lại.");
        }
        return "redirect:/follow";
    }
    @GetMapping("/buy")
    public String loadPack(ModelMap model){
        user = userService.findUserById(user.getIdUser());
        model.addAttribute("list", premiumService.getPremiumList());
        model.addAttribute("user",user);
        return "buy";
    }
    @GetMapping("/buy/get")
    public String buyPack(ModelMap model ,@RequestParam("id")int idpack, RedirectAttributes redirect){
        try{
            DetailPurchase detail = new DetailPurchase();
            detail.setUser(user);
            Premium pack = premiumService.getPackById(idpack);
            detail.setPremium(pack);
            detail.setPoint(pack.getPoint());
            detail.setDatePurchase(new java.util.Date());
            detail.setPrice(pack.getPrice());
            detailPurchaseService.save(detail);
            redirect.addFlashAttribute("message", "Mua gói thành công! Cảm ơn bạn đã ủng hộ.");
        }catch (Exception e){
            e.printStackTrace();
        }

        return "redirect:/buy";
    }
    @RequestMapping("/watch")
    public String loadWatch(ModelMap model, @RequestParam(value = "id") int id, @RequestParam(value = "ep") int ep) {
        model.addAttribute("user", user);
        model.addAttribute("film", filmService.getFilmById(id));
        model.addAttribute("currentEp", episodeService.getEpById(ep));
        Episode episode = episodeService.getEpById(ep);
        episode.setView(episode.getView()+1);
        episodeService.addEps(episode);
        return "watch";
    }

    @RequestMapping("/film-info")
    public String filmInfoPage(@RequestParam("id")int id, ModelMap model){
        user = userService.findUserById(user.getIdUser());
        model.addAttribute("user",user);
        model.addAttribute("film", filmService.getFilmById(id));
        model.addAttribute("check", buyFilmService.checkFilmInMyFilmList(id,user.getIdUser()));
        return "filmInfo";
    }
    @RequestMapping("/buy-film")
    public String buyFilm(ModelMap model, @RequestParam("id") int id, RedirectAttributes redirect){
        try{
            BuyFilm buy = new BuyFilm();
            buy.setFilm(filmService.getFilmById(id));
            buy.setUser(user);
            buy.setPrice(filmService.getFilmById(id).getPrice());
            buyFilmService.save(buy);
            redirect.addFlashAttribute("message", "Mua phim thành công! Chúc bạn thưởng thức phim vui vẻ.");
        }catch (Exception e){
            redirect.addFlashAttribute("message", "Xảy ra lỗi trong quá trình mua phim. Điểm đã giao dịch sẽ được hoàn trà.");
        }
        return "redirect:/film-info?id="+id;
    }
    @GetMapping("/my-film")
    public String loadMyFilm(ModelMap model){
        model.addAttribute("user", user);
        model.addAttribute("list",filmService.getMyFilm(user.getIdUser()));
        return "my_film";
    }
    @RequestMapping("/search")
    public String search(ModelMap model, @RequestParam(value = "keyword") String keyword) {
        model.addAttribute("user", user);
        model.addAttribute("keyword", keyword);
        model.addAttribute("filmList", filmService.searchFilm(keyword));

        return "search";
    }

    ///login-register



    //Login_Register
    @GetMapping("/")
    public String showLoginPage()throws Exception {
        user = null;
        return "login";
    }
    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, RedirectAttributes redirect, HttpSession session) throws Exception{
        Account account = accountService.findAccountByUsernameAndPassword(username,password);
        System.out.println("password: " + password);
        if (account == null) {
            redirect.addFlashAttribute("message","Sai tài khoản hoặc mật khẩu!");
            return "redirect:/";
        }else{
            if(!account.isStatus()){
                redirect.addFlashAttribute("message","Tài khoản của bạn đã bị khóa!");
                return "redirect:/";
            }else{
                user = account.getUser();
                return "redirect:/home";
            }
        }

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
                user.setGender("Không nói");
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
        mailService.sendSimpleMail(new EmailDetails(user.getEmail(), "Mã xác nhận của bạn là: "+Integer.toString(codeConfirm), "Thư xác nhận"));
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