package com.kos.tr.news.board.dto;

import com.kos.tr.news.member.entity.Member;
import lombok.Getter;

@Getter
public class PostDTO {

    private String id;
    private String subject;
    private String memo;
    private String file;
    private int viewCount;
    private String deleteYn;
    private String insertDate;
    private String updateDate;

    private Member member;

    public void setWriter(Member member){
        this.member = member;
    }
}
