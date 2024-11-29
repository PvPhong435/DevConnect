package com.dev.Model;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="users")
public class User {
	@Id
	String username;
	String password;
	String role;
	String fullname;
	String email;
	String phone;
	String address;
	String img;
	@JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;
	@OneToMany(mappedBy = "author")
    private Set<Article> articles;
	
}
