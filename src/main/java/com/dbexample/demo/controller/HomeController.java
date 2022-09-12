package com.dbexample.demo.controller;

import com.dbexample.demo.model.Person;
import com.dbexample.demo.repo.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class HomeController
{
    @Autowired
    private PersonRepository personRepository;



    @RequestMapping(value = "/user-info/{id}", method = RequestMethod.GET)
    public String getUserInfo(@PathVariable("id") long id, Model model)
    {
        Person result = personRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", result);

        return "/users/users-info";
    }

    @GetMapping("/user-edit/{id}")
    public String getUserEditPage(@PathVariable("id") long id, Model model)
    {
        Person result = personRepository
                .findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

        model.addAttribute("user", result);

        return "/users/users-edit";
    }

    @RequestMapping(value = "/user-update/{id}", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user") @Valid Person person,
                             BindingResult bindingResult, @PathVariable("id") long id)
    {
        person.setId(id);

        if(bindingResult.hasErrors())
        {
            return "/users/users-edit";
        }

        personRepository.save(person);

        return "redirect:/users";
    }

    @RequestMapping(value = "/user-delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") long id)
    {
        Person person = personRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));
        personRepository.delete(person);

        return "redirect:/users";
    }

    @GetMapping("/users")
    public String getMainPage(Model model)
    {
        Iterable<Person> users = personRepository.findAll();

        model.addAttribute("users", users);

        return "/users/users-main";
    }

    @PostMapping("/create-user")
    public String createNewUser(@ModelAttribute("user") @Valid Person person, BindingResult bindingResult)
    {
        if (bindingResult.hasErrors()) {
            return "/users/users-add";
        }
        personRepository.save(person);

        return "redirect:/users";
    }

    @GetMapping("/new-user")
    public String getAddUserPage(@ModelAttribute("user") Person person, Model model)
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
        List<Person> result = personRepository.findByLastname(lastname);
        model.addAttribute("result", result);
        return "/users/users-search";
    }

    @PostMapping("/users/search")
    public String simpleSearch(@RequestParam String lastname, Model model)
    {
        List<Person> result = personRepository.findByLastnameContains(lastname);
        model.addAttribute("result", result);
        return "/users/users-search";
    }
}
