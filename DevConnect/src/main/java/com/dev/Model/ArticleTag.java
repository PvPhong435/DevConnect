package com.dev.Model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "articletags")
public class ArticleTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;

    @ManyToOne
    @JoinColumn(name = "tagid")
    private Tag tag;

}
