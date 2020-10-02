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
    @Autowired
    UserRepository userRepository;

    @RequestMapping(value = {"/login"}, method = {RequestMethod.GET, RequestMethod.POST})
    public String loginIndex()
    {
        return "loginTest";
    }

    @PostMapping("/loginSubmission")
    public String login(@RequestParam(value="username") String username,
                               @RequestParam(value="password") String password,
                                Model model) {
        User currentUser = new User(username, password);
        boolean isAdmin = false;
        boolean isLoggedIn = false;
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
            return "index";
        } else {
            return "redirect:/login";
        }
    }
}
