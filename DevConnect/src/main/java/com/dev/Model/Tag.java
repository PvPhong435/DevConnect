package com.dev.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tags")
public class Tag {

    @Id
    @Column(name = "tagid")
    private String id;
    private String nametag;
    @JsonIgnore
    @ManyToMany(mappedBy = "tags")
    private List<Article> articles;

}
