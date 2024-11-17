package com.dev.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @Column(name = "tagid")
    private String id;
    private String nametag;
    @ManyToMany(mappedBy = "tags")
    private List<Article> articles;

}
