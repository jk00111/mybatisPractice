package com.kos.tr.news.login.controller;

import com.kos.tr.news.login.dto.LoginDTO;
import com.kos.tr.news.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @PostMapping("/check")
    public ResponseEntity<Boolean> loginCheck(LoginDTO dto){
        return ResponseEntity.ok(service.loginCheck(dto));
    }
}
