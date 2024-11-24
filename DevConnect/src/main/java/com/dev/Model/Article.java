package com.dev.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

    @Id
    @Column(name = "articleid")
    private String id;
    private String title;
    private String author;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createat;
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateat;
    @ManyToMany
    @JoinTable(
            name = "articletags",
            joinColumns = @JoinColumn(name = "articleid"),
            inverseJoinColumns = @JoinColumn(name = "tagid")
    )
    private Set<Tag> tags;
    @JsonIgnore
    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    private List<Comment> comments;
    @Transient
    private String titleSlug;

}
