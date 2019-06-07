package pl.coderslab.user;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.AuthHandler;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
@SessionAttributes({"userSession", "isLogged"})
public class UserController {
    private final UserService userService;
    private final AuthHandler authHandler;
    private final UserRepository userRepository;

    public UserController(UserService userService, AuthHandler authHandler, UserRepository userRepository) {
        this.userService = userService;
        this.authHandler = authHandler;
        this.userRepository = userRepository;
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
            userRepository.save(user);
            userService.setSession(user);
            return "redirect:/";
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
        return "users/userEditForm";
    }

    @PostMapping("/edit")
    public String editUser(@ModelAttribute("user") @Validated(ValidationEditUser.class) User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "users/userEditForm";
        }
        if (userService.isNotExistAnotherUserWithEmail(user)) {
            userService.edit(user);
            model.addAttribute("userSession", authHandler.getUser());
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "User with this email exists");
            return "users/userEditForm";
        }
    }

    @GetMapping("/delete")
    public String deleteUser(Model model) {
        if (authHandler.isLogged()) {
            userService.delete(authHandler.getUser());
            model.addAttribute("isLogged", authHandler.isLogged());
            model.addAttribute("userSession", authHandler.getUser());
        }
        return "redirect:/";
    }

    @GetMapping("/logout")
    public String logout(Model model) {
        authHandler.setLogged(false);
        model.addAttribute("isLogged", authHandler.isLogged());
        return "redirect:/";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "users/loginPage";
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam("email") String email, @RequestParam("password") String password, Model model) {
        if (userService.validateUserAndSetSession(email, password)) {
            model.addAttribute("isLogged", authHandler.isLogged());
            model.addAttribute("userSession", authHandler.getUser());
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Wrong login or password");
            return "users/loginPage";
        }
    }

    @GetMapping("/changePassword")
    public String passwordForm() {
        return "users/passwordForm";
    }

    @PostMapping("/changePassword")
    public String loginUser(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword, @RequestParam("confirmPassword") String confirmPassword, Model model) {
        User user = authHandler.getUser();
        if (BCrypt.checkpw(currentPassword, user.getPassword()) && newPassword.equals(confirmPassword) && newPassword.length() > 0) {
            user.setPassword(newPassword);
            userRepository.save(user);
            authHandler.setUser(user);
            model.addAttribute("userSession", user);
            return "redirect:/";
        } else {
            model.addAttribute("error", true);
            model.addAttribute("errorMsg", "Wrong password");
            return "users/passwordForm";
        }
    }

    @GetMapping("/details")
    public String userDetails(Model model) {
        model.addAttribute("user",authHandler.getUser());
        return "users/userDetails";
    }

}
