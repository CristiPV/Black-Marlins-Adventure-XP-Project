package com.blackmarlins.adventurexp.controller;

import com.blackmarlins.adventurexp.model.User;
import com.blackmarlins.adventurexp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class LoginController {

    private static boolean isAdmin;
    private static boolean isLoggedIn;

    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String loginIndex()
    {
        return "login";
    }

    @PostMapping("/loginSubmission")
    public String login(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                                Model model, Model model1) {
        User currentUser = new User(username, password);
        isAdmin = false;
        isLoggedIn = false;
        List<User> users = new ArrayList<>();
        users.addAll(userRepository.findAll());

        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).equals(currentUser)) {
                isLoggedIn = true;
                break;
            }
        }

        if (isLoggedIn) {
            if (username.equals("owner")) {
                isAdmin = true;
            }
            model.addAttribute("isAdmin", isAdmin);
            model1.addAttribute("username", username);
            return "welcome";
            //return "redirect:/activities/list";
        } else {
            return "redirect:/login";
        }
    }

    public static boolean isAdmin() {
        return isAdmin;
    }

    public static boolean isLoggedIn() {
        return isLoggedIn;
    }
}
