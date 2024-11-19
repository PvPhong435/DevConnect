package com.dev.Model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name="articles")
@Data
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
	
}
