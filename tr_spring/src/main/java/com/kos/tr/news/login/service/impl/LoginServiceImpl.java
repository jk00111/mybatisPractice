package com.kos.tr.news.login.service.impl;

import com.kos.tr.news.login.dto.LoginDTO;
import com.kos.tr.news.login.repository.LoginRepository;
import com.kos.tr.news.login.service.LoginService;
import com.kos.tr.news.member.entity.Member;
import com.kos.tr.news.member.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService {

    private final LoginRepository repository;
    private final MemberService memberService;

    @Override
    public boolean loginCheck(LoginDTO dto) {
        Member member = memberService.findOne(dto.getLongId());

        if (repository.loginCheck(member)){
            return true;
        }

        loginFail(member);
        return false;
    }

    private void loginFail(Member member){
        increaseFailCount(member);

        if (member.isLock()){
            blockMember(member);
        }
    }

    private void increaseFailCount(Member member) {
        member.addFailCount();
        repository.updateFail(member);
    }

    private void blockMember(Member member){
        repository.lock(member);
    }
}
