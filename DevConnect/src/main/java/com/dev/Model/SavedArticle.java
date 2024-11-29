package com.dev.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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