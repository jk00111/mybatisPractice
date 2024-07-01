package com.kos.tr.news.login.controller;

import com.kos.tr.news.login.dto.LoginDTO;
import com.kos.tr.news.login.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final LoginService service;

    @GetMapping
    public ResponseEntity<Boolean> loginCheck(LoginDTO dto){
        return ResponseEntity.ok(service.loginCheck(dto));
    }
}
