package com.dev.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@SuppressWarnings("serial")
@Data
@Entity
@Table(name="Users")
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
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval = true)
	List<SavedArticle> savedArticle;
}
