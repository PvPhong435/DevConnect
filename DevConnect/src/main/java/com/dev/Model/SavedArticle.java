package com.dev.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Table(name="saved_articles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SavedArticle {

	@EmbeddedId
	SavedArticleKey id;

	@ManyToOne
	@MapsId("user_id")
	@JoinColumn(name="user_id")
	User user;
	
	@ManyToOne
	@MapsId("article_id")
	@JoinColumn(name="article_id")
	Article article;
	
	@Temporal(TemporalType.TIMESTAMP)
	Date save_date;
	
	@Column(name="read")
	int read;
	
}
