package Rin.compani.H2DBUser.Controllers;

import Rin.compani.H2DBUser.Aspects.TrackUserAction;
import Rin.compani.H2DBUser.Model.User;
import Rin.compani.H2DBUser.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
/*
класс контроллеров
запросы по полю /users
*/
@Controller
@RequestMapping("/users")
public class UserController {
    /*
    подключаем сервисе UserService
    */
    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /* переход по ссылке /users */
    @GetMapping("/")
    public String findAllUsers(Model model){
        List<User> users = userService.findAllUsers();
        model.addAttribute("users",users);
        return "user-list.html";
    }

    /*   преход на страницу создания нового пользователя */
    @GetMapping("/create")
    public String createUserForm(User user){return "user-create.html";}

    /*Обработка страницы создания нового пользователя*/
    @PostMapping("/create")
    @TrackUserAction
    public String createUser(User user){
        userService.saveUser(user);
        return "redirect:/users/";
    }
    /*Удаление пользователя */
    @GetMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") int id){
        userService.deleteUser(id);
        return  "redirect:/users/";
    }
    /*переход на форму редактирования пользователя*/
    @GetMapping("/edit/{id}")
    public String editUserForm(@PathVariable("id") int id, Model model) {
        User user = userService.getUserById(id);
        model.addAttribute("userOld",user);
        return "user-update";
    }
    /*Маппер на редактирование пользователя*/
    @PostMapping ("/edit/{id}")
    public String editUser(@PathVariable("id") int id, User user) {
        userService.editUser(id,user);
        return "redirect:/users/";
    }
}
