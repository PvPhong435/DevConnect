package com.dev.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

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
    private String role;
    private String fullname;
    private String email;
    private String phone;
    private String address;
    private String img;
    @JsonIgnore
    @OneToMany(mappedBy = "user")
    private Set<Comment> comments;

}
