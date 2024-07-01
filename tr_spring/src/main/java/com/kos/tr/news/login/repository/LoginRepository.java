package com.kos.tr.news.login.repository;

import com.kos.tr.news.member.entity.Member;
import org.springframework.stereotype.Repository;

@Repository
public interface LoginRepository {

    int loginCheck(Member member);
    void updateFail(Member member);
    void lock(Member member);
}
