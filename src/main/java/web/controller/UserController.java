package web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

@RequestMapping("/users")
@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getAllUsers(Model model) {
        model.addAttribute("users", userService.getAllUsers());
        return "users/userslist";
    }

    @GetMapping("/new")
    public String addNewUser(Model model) {
        model.addAttribute("user", new User());

        return "users/new";
    }

    @PostMapping
    public String saveUser(@ModelAttribute("user") User user) {
        userService.addUser(user);

        return "redirect:/users";
    }

    @GetMapping("/{email}/edit")
    public String editUser(Model model, @PathVariable("email") String email) {
        model.addAttribute("user", userService.getUser(email));
        return "users/edit";
    }

    @PatchMapping("/{email}")
    public String updateUser(@ModelAttribute("user") User user, @PathVariable("email") String email) {
        userService.updateUser(email, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{email}")
    public String delete(@PathVariable("email") String email) {
        userService.deleteUser(email);
        return "redirect:/users";
    }
}
