package com.example.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    /*
    new
     */
    @GetMapping("/articles/new")
    public String new_form() {
        return "new";
    }

    /*
    * Find All
    * */
    @GetMapping("/articles")
    public String list(Model model) {
        Iterable<Article> list = articleRepository.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/articles/create")
    public String create(@ModelAttribute ArticleForm form, Model model) {
        Article article = new Article(form.getTitle(), form.getContent(), form.getDate());
        Article result = articleRepository.save(article);

        model.addAttribute("article", form);    //얘도 DTO 이용한 거

//        return "redirect:/articles/" + result.getId();
        return "redirect:/articles/";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable("id") Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        article.setContent(article.getContent().replace("\n", "<br>"));
        article.setContent(article.getContent().replace("<", "&lt"));   //cross site script injection 방지
        article.setContent(article.getContent().replace(">", "&gt"));   //cross site script injection 방지
        model.addAttribute("article", article);
        return "show";
    }

}
