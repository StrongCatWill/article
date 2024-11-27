package com.example.article;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@Entity
@NoArgsConstructor
public class Article {

    @Id @GeneratedValue
    private Long Id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDate date;

    public Article(String title, String content, LocalDate date) {
        this.title = title;
        this.content = content;
        this.date = LocalDate.now();
    }
}
