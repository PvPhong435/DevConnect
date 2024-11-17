package com.dev.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"comments", "tags"})
@ToString(exclude = {"comments", "tags"})
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "articleid")
    private String id;
    private String title;
    private String author;
    private String content;
    private Date createat;
    private Date updateat;
    @ManyToMany
    @JoinTable(
            name = "articletags",
            joinColumns = @JoinColumn(name = "articleid"),
            inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    private Set<Tag> tags;
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;

}
