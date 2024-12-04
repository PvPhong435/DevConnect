package com.dev.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    @OneToMany(mappedBy = "tag",cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ArticleTag> articleTags;

    public String getTagByName() {
        return id.replace("#", "");
    }

}
