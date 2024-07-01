package com.kos.tr.news.board.service.impl;

import com.kos.tr.news.board.dto.PostDTO;
import com.kos.tr.news.board.entity.Post;
import com.kos.tr.news.board.repository.BoardRepository;
import com.kos.tr.news.board.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class BoardServiceImpl implements BoardService {

    private final BoardRepository repository;

    @Override
    public void createPost(PostDTO dto) {
        Post post = Post.newPost(dto);

        repository.create(post);
    }

    @Override
    public void updatePost(PostDTO dto) {
        Post post = repository.findOne(dto.getId());
        post.updateContents(dto);
        repository.update(post);
    }

    @Override
    public void deletePost(String id) {
        Post post = repository.findOne(id);
        post.unused();
        repository.delete(post);
    }

    @Override
    public List<Post> findAll() {
        return repository.findAll();
    }

    @Override
    public Post findOne(String id) {
        Post post = repository.findOne(id);
        increaseViewCount(post);
        return post;
    }

    private void increaseViewCount(Post post) {
        post.addViewCount();
        repository.update(post);
    }
}
