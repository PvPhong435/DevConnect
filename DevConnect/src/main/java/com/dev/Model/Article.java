package com.dev.Model;

import com.dev.Util.SlugUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "articles")
public class Article {

	@Id
	private String articleID;
	private String title;
	private String content;
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;
	@Temporal(TemporalType.TIMESTAMP)
	private Date createat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateat;
	private int views;

	@OneToMany(mappedBy="article",cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ArticleImages> images;
	@ManyToMany
	@JoinTable(
			name = "articletags",
			joinColumns = @JoinColumn(name = "articleid"),
			inverseJoinColumns = @JoinColumn(name = "tagid")
	)
	private Set<Tag> tags;
	@JsonIgnore
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Comment> comments;
	@ManyToOne
	@JoinColumn(name = "author")
	private User author;

	public String getTitleSlug() {
		return SlugUtil.toSlug(title);
	}

	public void incrementViews() {
		views += 1;
	}

}
