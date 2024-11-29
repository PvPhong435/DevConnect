package com.dev.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="article_imgs")
public class ArticleImages {
	@Id
	String id;
	@Column(name="img_url")
	String imgURL;
	@ManyToOne
	@JoinColumn(name="article_id")
	Article article;
}