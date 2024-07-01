package com.kos.tr.news.login.service;

import com.kos.tr.news.login.dto.LoginDTO;
import com.kos.tr.news.member.entity.Member;

public interface LoginService {
    boolean loginCheck(LoginDTO dto);
}
