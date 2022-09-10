package com.dbexample.demo.controller;

import com.dbexample.demo.model.User;
import com.dbexample.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/user-info/{id}", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable("id") long id, Model model)
    {
        User result = userRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", result);

        return "/users/users-info";
    }

    @GetMapping("/user-edit/{id}")
    public String getUserEditPage(@PathVariable("id") long id, Model model)
    {
        User result = userRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", result);

        return "/users/users-edit";
    }

    @RequestMapping(value = "/user-update/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult, @PathVariable("id") long id)
    {
        user.setId(id);

        if(bindingResult.hasErrors())
        {
            return "/users/users-edit";
        }

        userRepository.save(user);

        return "redirect:/users";
    }

    @RequestMapping(value = "/user-delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id)
    {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        userRepository.delete(user);

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getMainPage(Model model)
    {
        Iterable<User> users = userRepository.findAll();

        model.addAttribute("users", users);

        return "/users/users-main";
    }

    @PostMapping("/create-user")
    public String createNewUser(@ModelAttribute("user") @Valid User user, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/users/users-add";
        }
        userRepository.save(user);

        return "redirect:/users";
    }

    @GetMapping("/new-user")
    public String getAddUserPage(@ModelAttribute("user") User user, Model model)
    {
        return "/users/users-add";
    }

    @GetMapping("search-user")
    public String getFilter(Model model)
    {
        return "/users/users-search";
    }

    @PostMapping("/users/search-advanced")
    public String searchAdvanced(@RequestParam String lastname, Model model)
    {
        List<User> result = userRepository.findByLastname(lastname);
        model.addAttribute("result", result);
        return "/users/users-search";
    }

    @PostMapping("/users/search")
    public String simpleSearch(@RequestParam String lastname, Model model)
    {
        List<User> result = userRepository.findByLastnameContains(lastname);
        model.addAttribute("result", result);
        return "/users/users-search";
    }
}
