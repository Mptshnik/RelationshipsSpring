package com.dbexample.demo.controller;

import com.dbexample.demo.model.Post;
import com.dbexample.demo.model.User;
import com.dbexample.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/users")
    public String getMainPage(Model model)
    {
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("users", users);

        return "users-main";
    }

    @PostMapping("/create-user")
    public String createNewUser(@RequestParam String firstname,
                                @RequestParam String lastname,
                                @RequestParam String middlename,
                                @RequestParam int age,
                                @RequestParam int weight,
                                @RequestParam int height,
                                Model model)
    {
        User user = new User(firstname, lastname, middlename,
                age, height, weight);
        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping(path = "/new-user")
    public String getAddBlog(Model model)
    {
        return "users-add";
    }

    @GetMapping("search-user")
    public String getFilter(Model model)
    {
        return "users-search";
    }

    @PostMapping("/users/search-advanced")
    public String searchAdvanced(@RequestParam String lastname, Model model)
    {
        List<User> result = userRepository.findByLastname(lastname);
        model.addAttribute("result", result);
        return "users-search";
    }

    @PostMapping("/users/search")
    public String simpleSearch(@RequestParam String lastname, Model model)
    {
        List<User> result = userRepository.findByLastnameContains(lastname);
        model.addAttribute("result", result);
        return "users-search";
    }
}
