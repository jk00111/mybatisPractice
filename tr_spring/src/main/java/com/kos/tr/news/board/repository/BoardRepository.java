package com.kos.tr.news.board.repository;


import com.kos.tr.news.board.entity.Post;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BoardRepository {
    void create(Post post);
    void update(Post post);
    void delete(Post post);
    List<Post> findAll();
    Post findOne(String id);
}
