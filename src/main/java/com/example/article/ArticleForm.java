package com.example.article;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

// Article DTO
@Data
@NoArgsConstructor
public class ArticleForm {
    private Long id;
    private String title;
    private String content;
    private LocalDate date;

    public ArticleForm(Article article) {
        this.id = article.getId();
        this.title = article.getTitle();
        this.content = article.getContent();
        this.date = article.getDate();
    }
}
