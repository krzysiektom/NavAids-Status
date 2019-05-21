package pl.coderslab.user;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {
    private final UserService userService;

    private final AuthHandler authHandler;

    public UserController(UserService userService, AuthHandler authHandler) {
        this.userService = userService;
        this.authHandler = authHandler;
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        User user = new User();
        model.addAttribute(user);
        return "users/userForm";
    }

    @PostMapping("/add")
    public String addUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/userForm";
        }
        if (userService.isNotExistEmail(user)) {
            userService.save(user);
            userService.setSession(user);
            return "redirect:/tweet/main";//???
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "User with this email exists");
            return "users/userForm";
        }
    }

    @GetMapping("/edit")
    public String editForm(Model model) {
        User user = authHandler.getUser();
        model.addAttribute("user", user);
        return "users/userForm";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Valid User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/userForm";
        }
        if (userService.isNotExistAnotherUserWithEmail(user)) {
            user.setId(authHandler.getUser().getId());
            userService.save(user);
            userService.setSession(user);
            return "redirect:/tweet/main";//??
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "User with this email exists");
            return "users/userForm";
        }
    }

    @GetMapping("/delete")
    public String deleteUser() {
        if (authHandler.isLogged()) {
            userService.delete(authHandler.getUser().getId());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout() {
        authHandler.setLogged(false);
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "users/loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.validateUserAndSetSession(email, password)) {
            return "redirect:/tweet/main";//??
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Wrong login or password");
            return "users/loginPage";
        }
    }

}
