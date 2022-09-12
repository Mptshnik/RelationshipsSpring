package com.dbexample.demo.controller;

import com.dbexample.demo.model.Role;
import com.dbexample.demo.model.User;
import com.dbexample.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collections;
import java.util.Map;

@Controller
public class RegistrationController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/register")
    public String register()
    {
        return "registration";
    }

    @PostMapping("/register")
    public String addUser(User user, Model model)
    {
        User registeredUser = userRepository.findByUsername(user.getUsername());
        if(registeredUser != null)
        {
            model.addAttribute("message","Пользователь с таким именем уже существует");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.USER));
        userRepository.save(user);
        return "redirect:/login";
    }
}
