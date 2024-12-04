package com.dev.Model;

import com.dev.Util.SlugUtil;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.List;

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
	@Temporal(TemporalType.TIMESTAMP)
	private Date createat;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updateat;
	private int views;

	@OneToMany(mappedBy="article",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ArticleImages> images;
	@OneToMany(mappedBy = "article",cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
	private List<ArticleTag> articleTags;
	@JsonIgnore
	@OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
	private List<Comment> comments;
	@ManyToOne
	@JoinColumn(name = "author")
	private User author;
	@ManyToOne
	@JoinColumn(name="categoryid")
	private Category category;

	public String getTitleSlug() {
		return SlugUtil.toSlug(title);
	}

	public void incrementViews() {
		views += 1;
	}

}
