package com.dev.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class SavedArticleKey {
	@Column(name="user_id")
	String user_id;
	@Column(name="article_id")
	String article_id;
}
