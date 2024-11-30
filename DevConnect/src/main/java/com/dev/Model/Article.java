package com.dev.Model;

import com.dev.Util.SlugUtil;
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
    @ManyToOne
    @JoinColumn(name = "author")
    private User author;

    public String getTitleSlug() {
        return SlugUtil.toSlug(title);
    }
    
}
