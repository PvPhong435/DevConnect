package com.dev.Model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="Categories")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Category {
	@Id
	String categoryId;
	String categoryName;
	@OneToMany(mappedBy="category", cascade=CascadeType.ALL, orphanRemoval=true)
	List<Article> articles;
}
