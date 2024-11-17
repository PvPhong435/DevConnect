package com.dev.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {

    @Id
    private String username;
    private String role;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String img;
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

}
