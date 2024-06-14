package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String getUsers(Model model) {
        model.addAttribute("users", userService.getUsers());

        return "getUsers";
    }

    @GetMapping("/id")
    public String showUserById(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("user", userService.showUserById(id));

        return "showUserById";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping()
    public String saveUser(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect: /";
    }

    @GetMapping("/updateUser")
    public String updateUserById(@RequestParam(value = "id", required = false) Long id,
                                 Model model) {
        model.addAttribute("user", userService.showUserById(id));
        return "updateUserById";
    }
    @PostMapping("/update")
    public String update(@RequestParam(value = "id", required = false) Long id,
                         @ModelAttribute("user") User user) {
        userService.updateUserById(id, user);
        return "redirect: /";
    }
    @GetMapping("/d")
    public String deleteUser(@ModelAttribute("user") User user) {
        userService.deleteUserById(user.getId());
        return "redirect: /";
    }
}