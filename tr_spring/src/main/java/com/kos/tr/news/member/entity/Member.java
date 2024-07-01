package com.kos.tr.news.member.entity;

import lombok.Getter;

@Getter
public class Member {

    private String id;
    private String Name;
    private String longId;
    private String pw;
    private int lognFailCnt;

    public void addFailCount(){
        this.lognFailCnt++;
    }

    public boolean isLock(){
        return this.lognFailCnt >= 5;
    }
}
