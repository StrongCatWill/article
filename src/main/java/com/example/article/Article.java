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
    private Long id;

    @Column
    private String title;

    @Column
    private String content;

    @Column
    private LocalDate date;

    public Article(Long id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.date = LocalDate.now();
    }
}
