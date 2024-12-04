package com.dev.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
	private String password;
    private String role;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String img;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
    @OneToMany(mappedBy = "author")
    private Set<Article> articles;
    @OneToMany(mappedBy= "user", fetch = FetchType.EAGER)
    private Set<SavedArticle> savedArticles;
}
