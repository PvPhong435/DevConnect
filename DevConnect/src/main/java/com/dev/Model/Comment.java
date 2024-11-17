package com.dev.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "commentid")
    private String id;
    private String content;
    private Date createat;
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @Transient
    private String createAtAsString;

}
