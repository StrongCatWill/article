package com.example.article;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/articles/new")
    public String new_form() {
        return "new";
    }

    @GetMapping("/articles")
    public String list(Model model) {
        Iterable<Article> list = articleRepository.findAll();
        model.addAttribute("list", list);
        return "index";
    }

    @PostMapping("/articles/create")
    public String create(@ModelAttribute ArticleForm form, Model model) {
        Article article = new Article(null, form.getTitle(), form.getContent());
        Article result = articleRepository.save(article);

        model.addAttribute("article", form);    //얘도 DTO 이용한 거

        return "redirect:/articles/" + result.getId();
//        return "redirect:/articles/";
    }

    @GetMapping("/articles/{id}")
    public String show(@PathVariable Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        article.setContent(article.getContent().replace("\n", "<br>"));
//        article.setContent(article.getContent().replace("<", "&lt"));   //cross site script injection 방지
//        article.setContent(article.getContent().replace(">", "&gt"));   //cross site script injection 방지
        model.addAttribute("article", article);
        return "show";
    }

    @GetMapping("/articles/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            ArticleForm form = new ArticleForm(article);
            model.addAttribute("article", form);
        }
        return "edit";
    }


    @PostMapping("/articles/update")
    public String update(@ModelAttribute ArticleForm form, RedirectAttributes re) {

        Article article = articleRepository.findById(form.getId()).orElse(null);
        if(article==null) {
            re.addFlashAttribute("alert_msg", "에러: 번호 오류");
            return "redirect:/articles";
        }

        article.setTitle(form.getTitle());
        article.setContent(form.getContent());
        articleRepository.save(article);

        re.addFlashAttribute("alert_msg", "업데이트 되었습니다.");

        return "redirect:/articles/" + form.getId();
    }

    @GetMapping("articles/{id}/delete")
    public String delete(@PathVariable("id") Long id, RedirectAttributes ra) {
        Article article = articleRepository.findById(id).orElse(null);
        if (article != null) {
            articleRepository.delete(article);
            ra.addFlashAttribute("alert_msg", "삭제했습니다");
        }else{
            ra.addFlashAttribute("alert_msg", "에러: 번호 오류");
        }
        return "redirect:/articles";
    }

}
