package com.dbexample.demo.controller;

import com.dbexample.demo.model.Post;
import com.dbexample.demo.repo.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController
{
    @Autowired
    private PostRepository postRepository;


    @GetMapping ("/main")
    public String getMainPage(Model model)
    {
        Iterable<Post> posts = postRepository.findAll();
        model.addAttribute("posts", posts);
        return "blog-main";
    }

    @PostMapping(path = "/create-post")
    public String createNewPost(@RequestParam(value = "title") String title,
                             @RequestParam(value = "anons") String anons,
                             @RequestParam(value = "full_text") String fullText,
                             Model model)
    {
        Post post = new Post(title, anons, fullText);
        postRepository.save(post);

        return "redirect:/";
    }

    @GetMapping(path = "/new-post")
    public String getAddBlog(Model model)
    {
        return "blog-add";
    }

    @GetMapping(path = "filter")
    public String getFilter(Model model)
    {
        return "blog-filter";
    }

    @PostMapping("/blog/filter/result")
    public String blogResult(@RequestParam String title, Model model)
    {
        List<Post> result = postRepository.findByTitleContains(title);
        model.addAttribute("result", result);
        return "blog-filter";
    }
}
