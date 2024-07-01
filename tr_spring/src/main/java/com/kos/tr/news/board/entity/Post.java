package com.kos.tr.news.board.entity;

import com.kos.tr.news.board.dto.PostDTO;
import com.kos.tr.news.common.enums.UseType;
import com.kos.tr.news.member.entity.Member;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalTime;

@Getter
@Builder
public class Post {

    private String id;
    private String subject;
    private String memo;
    private String file;
    private int viewCount;
    private UseType deleteYn;
    private String insertDate;
    private String updateDate;

    private Member member;

    public void addViewCount(){
        this.viewCount++;
    }

    public void updateContents(PostDTO dto){
        this.subject = dto.getSubject();
        this.memo = dto.getMemo();
        this.file = dto.getFile();
        this.updateDate = LocalTime.now().toString();
    }

    public void unused(){
        this.deleteYn = UseType.N;
    }

    public static Post newPost(PostDTO dto){
        return Post.builder()
                .subject(dto.getSubject())
                .memo(dto.getMemo())
                .memo(dto.getMemo())
                .deleteYn(UseType.Y)
                .insertDate(LocalTime.now().toString())
                .updateDate(LocalTime.now().toString())
                .build();
    }
}
