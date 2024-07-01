package com.kos.tr.news.board.controller;

import com.kos.tr.news.board.dto.PostDTO;
import com.kos.tr.news.board.entity.Post;
import com.kos.tr.news.board.service.BoardService;
import com.kos.tr.news.common.Session;
import com.kos.tr.news.member.entity.Member;
import com.kos.tr.news.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;
    private final MemberService memberService;

    @PostMapping
    public ResponseEntity<String> createPost(PostDTO dto, Session session){
        Member writer = memberService.findOne(session.getUserUid());
        dto.setWriter(writer);

        boardService.createPost(dto);
        return ResponseEntity.ok().build();
    }

    @PutMapping
    public ResponseEntity<String> updatePost(PostDTO dto){
        boardService.updatePost(dto);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<String> deletePost(String id){
        boardService.deletePost(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<Post>> findAll(){
        return ResponseEntity.ok(boardService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> findOne(@PathVariable String id){

        return ResponseEntity.ok(boardService.findOne(id));
    }
}
