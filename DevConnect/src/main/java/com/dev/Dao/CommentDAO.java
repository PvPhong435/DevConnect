package com.dev.Dao;

import com.dev.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentDAO extends JpaRepository<Comment, String> {


}
