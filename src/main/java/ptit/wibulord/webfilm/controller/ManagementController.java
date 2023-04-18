package ptit.wibulord.webfilm.controller;

import org.springframework.aop.scope.ScopedProxyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ptit.wibulord.webfilm.model.*;
import ptit.wibulord.webfilm.service.*;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@ComponentScan
@Controller
@RequestMapping("/management")
public class ManagementController {
    @Autowired
    CategoryService categoryService;
    @Autowired
    FilmService filmService;
    @Autowired
    UserService userService;
    @Autowired
    AccountService accountService;
    @Autowired
    PremiumService premiumService;

    StatisticService statisticService = new StatisticService();
    @GetMapping("")
    public String managementPage(){
        return "management/management";
    }
    @GetMapping("/filmCategory")
    public String management_CategoryPage(ModelMap model){
        List<Category> categoryList = categoryService.getCategoryList();
        model.addAttribute("categoryList", categoryList);
        return "management/management_category";
    }
    @PostMapping("/filmCategory/save")
    public String addCategory(ModelMap model , @RequestParam("name")String name, RedirectAttributes redirect){
        if(categoryService.findByName(name)==null){
            Category category = new Category();
            category.setCategoryName(name);
            categoryService.saveCategory(category);
            redirect.addFlashAttribute("message", "Thêm thể loại thành công!");
        }else{
            redirect.addFlashAttribute("message", "Tên thể loại đã tồn tại");
        }
        return "redirect:/management/filmCategory";
    }

    @PostMapping("/filmCategory/update/{id}")
    public String updateCategory(ModelMap model ,
                                 @PathVariable("id")int id,
                                 @RequestParam("name")String name,
                                 RedirectAttributes redirect){
        if(categoryService.findByName(name)==null){
            Category category = new Category();
            category.setIdCategory(id);
            category.setCategoryName(name);
            categoryService.saveCategory(category);
            redirect.addFlashAttribute("message", "Thêm thể loại thành công!");
        }else{
            redirect.addFlashAttribute("message", "Cập nhật thất bại.");
        }
        return "redirect:/management/filmCategory";
    }

    @PostMapping("/filmCategory/delete/{id}")
    public String deleteCategory(ModelMap model ,
                                 @PathVariable("id")int id,
                                 RedirectAttributes redirect){
        System.out.println(id);
        try{
            categoryService.deleteCategory(id);
            redirect.addFlashAttribute("message", "Xóa thể loại thành công!");
        }catch(Exception e){
            redirect.addFlashAttribute("message", "Xóa thất bại.");
        }
        return "redirect:/management/filmCategory";
    }

    @GetMapping("/Film")

    public String management_FilmPage(ModelMap model){
        List<Film> filmList = filmService.getFilms();
        model.addAttribute("filmList", filmList);
        return "management/management_film";
    }
    @GetMapping("/User")
    public String management_UserPage(ModelMap model){
        List<User> userList = userService.getUserList();
        model.addAttribute("userList", userList);
        return "management/management_user";
    }
    @PostMapping("User/status/{username}")
    public String activeAccount(@PathVariable("username")String username , RedirectAttributes redirect){
        try{
            Account account = accountService.findAccountByUsername(username);

            if(account.isStatus()==true)account.setStatus(false);
            else account.setStatus(true);
            accountService.addAccount(account);
            redirect.addFlashAttribute("message", "Thay đổi trạng thái tài khoản \"" + username+"\" thành công!");

        }catch (Exception e){
            redirect.addFlashAttribute("message", "Thay đổi trạng thái tài khoản \"" + username+"\" không thành công.");
        }
        return "redirect:/management/User";
    }

//    @PostMapping("User/disable/{username}")
//    public String disableAccount(@PathVariable("username")String username , RedirectAttributes redirect){
//        try{
//            Account account = accountService.findAccountByUsername(username);
//            account.setStatus(false);
//            accountService.addAccount(account);
//            redirect.addFlashAttribute("message", "Khóa tài khoản " + username+" thành công!");
//
//        }catch (Exception e){
//            redirect.addFlashAttribute("message", "Khóa tài khoản " + username+" không thành công.");
//        }
//        return "redirect:/User";
//    }
    @GetMapping("/Premium")
    public String management_PremiumPage(ModelMap model){
        List<Premium> premiumList = premiumService.getPremiumList();
        model.addAttribute("premiumList",premiumList);
        return "management/management_premium";
    }
    @PostMapping("/Premium/save")
    public String savePack(@RequestParam("price")long price,
                           @RequestParam("quantityDay")int quantity,
                           RedirectAttributes redirect){
        Premium premium = new Premium();
        premium.setPrice(price);
        premium.setNofDay(quantity);
        try{
            premiumService.addPack(premium);
            redirect.addFlashAttribute("message", "Thêm gói thành công!");
        }catch (Exception e){
            redirect.addFlashAttribute("message", "Thêm gói thất bại.");
        }
        return "redirect:/management/Premium";
    }
    @PostMapping("/Premium/update/{id}")
    public String updatePack(@PathVariable("id")int id
                            ,@RequestParam("price")long price,
                            @RequestParam("quantityDay")int quantity,
                            RedirectAttributes redirect){
        Premium premium = new Premium();
        premium.setPrice(price);
        premium.setNofDay(quantity);
        premium.setIdPackage(id);
        try{
            premiumService.addPack(premium);
            redirect.addFlashAttribute("message", "Cập nhật thông tin gói thành công!");
        }catch (Exception e){
            redirect.addFlashAttribute("message", "Cập nhật thông tin gói thất bại.");
        }
        return "redirect:/management/Premium";
    }

    @PostMapping("/Premium/delete/{id}")
    public String deletePack(
                                 @PathVariable("id")int id,
                                 RedirectAttributes redirect){
        try{
            premiumService.deletePack(id);
            redirect.addFlashAttribute("message", "Xóa gói thành công!");
        }catch(Exception e){
            redirect.addFlashAttribute("message", "Xóa gói thất bại.");
        }
        return "redirect:/management/Premium";
    }

    @GetMapping("/Revenue/GetDetail")
    public String firstLoadStatistic( RedirectAttributes redirect){
        String fromDate = "";
        String toDate = "";
        String title = "";
        SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
            fromDate = java.time.LocalDate.now().toString();
            toDate = java.time.LocalDate.now().toString();
            title = java.time.LocalDate.now().toString();

        List<RevenuePremium> list = statisticService.StatisticRevenuePremium(fromDate,toDate);
        redirect.addFlashAttribute("list", list);

        int totalRevenue = statisticService.totalRevenue(fromDate,toDate);
        redirect.addFlashAttribute("totalRevenue", totalRevenue);

        int totalBuy = statisticService.totalBuy(fromDate,toDate);
        redirect.addFlashAttribute("totalBuy", totalBuy);

        int totalRegister = statisticService.totalRegister(fromDate,toDate);
        redirect.addFlashAttribute("totalRegister", totalRegister);

        int totalUser = statisticService.totalUser(toDate);
        redirect.addFlashAttribute("totalUser", totalUser);
        redirect.addFlashAttribute("title", title);
        return "redirect:/management/Revenue";
    }
    @PostMapping("/Revenue/GetDetail")
    public String getDataRevenue(@RequestParam("day") String day,
                                 @RequestParam("month")String month,
                                 @RequestParam("year")String year, RedirectAttributes redirect){
        String fromDate = "";
        String toDate = "";
        String title = "";
        SimpleDateFormat Format = new SimpleDateFormat("dd/MM/yyyy");
        if(day.isBlank()&&month.isBlank()&&year.isBlank()){
            fromDate = java.time.LocalDate.now().toString();
            toDate = java.time.LocalDate.now().toString();
            title = java.time.LocalDate.now().toString();
        }
        else if(!day.isBlank()){
            fromDate = day;
            toDate = day;
            title = day;
        }else if(!month.isBlank()) {
            fromDate = year + "-" + month + "-01";
            String dayStr = "";
            switch (Integer.parseInt(month)) {
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12:
                    dayStr = "31";
                    break;
                case 4:
                case 6:
                case 9:
                case 11:
                    dayStr = "30";
                    break;
                default:
                    if (statisticService.isLeap(Integer.parseInt(year))) {
                        dayStr = "29";
                    } else {
                        dayStr = "28";
                    }
                    break;
            }
            toDate = year + "-" + month + "-" + dayStr;
            title = month+"-"+year;
        }else {
            fromDate = year + "-01-01";
            toDate = year + "-12-31";
            title = year;
            System.out.println(fromDate + "  " + toDate);
        }

        List<RevenuePremium> list = statisticService.StatisticRevenuePremium(fromDate,toDate);
        redirect.addFlashAttribute("list", list);

        int totalRevenue = statisticService.totalRevenue(fromDate,toDate);
        redirect.addFlashAttribute("totalRevenue", totalRevenue);

        int totalBuy = statisticService.totalBuy(fromDate,toDate);
        redirect.addFlashAttribute("totalBuy", totalBuy);

        int totalRegister = statisticService.totalRegister(fromDate,toDate);
        redirect.addFlashAttribute("totalRegister", totalRegister);

        int totalUser = statisticService.totalUser(toDate);
        redirect.addFlashAttribute("totalUser", totalUser);
        redirect.addFlashAttribute("title", title);
        return "redirect:/management/Revenue";
    }
    @GetMapping("/Revenue")
    public String management_RevenuePage(){
        return "management/management_statistic";
    }

}
