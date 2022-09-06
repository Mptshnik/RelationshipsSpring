package com.dbexample.demo.controller;

import com.dbexample.demo.model.Animal;
import com.dbexample.demo.repo.AnimalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AnimalController
{
    @Autowired
    private AnimalRepository animalRepository;

    @GetMapping("/animals")
    public String getMainPage(Model model)
    {
        Iterable<Animal> animals = animalRepository.findAll();

        model.addAttribute("animals", animals);

        return "animals-main";
    }

    @PostMapping("/create-animal")
    public String createNewUser(@RequestParam String name,
                                @RequestParam String color,
                                @RequestParam String speed,
                                @RequestParam int age,
                                @RequestParam int length,
                                Model model)
    {
        Animal animal = new Animal(name, speed, color, age, length);
        animalRepository.save(animal);

        return "redirect:/animals";
    }

    @GetMapping(path = "/new-animal")
    public String getAddBlog(Model model)
    {
        return "animals-add";
    }

    @GetMapping("search-animal")
    public String getFilter(Model model)
    {
        return "animals-search";
    }

    @PostMapping("/animals/search-advanced")
    public String searchAdvanced(@RequestParam String name, Model model)
    {
        List<Animal> animals = animalRepository.findByName(name);
        model.addAttribute("result", animals);
        return "animals-search";
    }

    @PostMapping("/animals/search")
    public String simpleSearch(@RequestParam String name, Model model)
    {
        List<Animal> animals = animalRepository.findByNameContains(name);
        model.addAttribute("result", animals);
        return "animals-search";
    }
}
