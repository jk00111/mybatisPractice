package com.kos.tr.news.board.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private String id;
    private String subject;
    private String memo;
    private String file;
    private int viewCount;
    private String deleteYn;
    private String insertDate;
    private String updateDate;

    private String userId;
}
