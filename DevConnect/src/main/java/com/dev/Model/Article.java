package com.dev.Model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Article {
	@Id
	String articleID;
	String title;
	String content;
	@ManyToOne
	@JoinColumn(name="categoryid")
	Category category;
	@ManyToOne
	@JoinColumn(name="author")
	User author;
	@Temporal(TemporalType.TIMESTAMP)
	Date createat;
	@Temporal(TemporalType.TIMESTAMP)
	Date updateat;
	@OneToMany(mappedBy="article",cascade = CascadeType.ALL, orphanRemoval = true)
	List<ArticleImages> images;
}