package com.dev.Model;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @Column(name = "commentid")
    private String id;
    private String content;
    @Temporal(TemporalType.TIMESTAMP)
    private Date createat = new Date();
    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;
    @ManyToOne
    @JoinColumn(name = "username")
    private User user;
    @Transient
    private String createAtAsString;

}
