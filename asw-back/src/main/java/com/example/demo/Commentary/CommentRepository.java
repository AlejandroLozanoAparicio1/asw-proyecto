package com.example.demo.Commentary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    @Query(value = "select * from comments_liked_by clb , comments c where clb.liked_by_username = :username and c.id  = clb.comment_id", nativeQuery = true)
    public List<Comment> getLikedByUsername(@Param("username")String username);
    @Query(value = "select nc.comments_id from news_comments nc  where nc.news_item_id  = :id", nativeQuery = true)
    public List<Long> getNewsComments(@Param("id")long id);
}
