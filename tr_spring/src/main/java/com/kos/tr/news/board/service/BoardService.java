package com.kos.tr.news.board.service;

import com.kos.tr.news.board.dto.PostDTO;
import com.kos.tr.news.board.entity.Post;

import java.util.List;

public interface BoardService {

    void createPost(PostDTO dto);
    void updatePost(PostDTO dto);
    void deletePost(String id);
    List<Post> findAll();
    Post findOne(String id);
}
