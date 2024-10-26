package com.anyj.backend.controller;

import com.anyj.backend.dto.request.RegisterRequest;
import com.anyj.backend.dto.response.CommonResponse;
import com.anyj.backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/auth")
public class AuthController {

    @Resource
    private UserService userService;

    @PostMapping("/register")
    public CommonResponse register(@RequestBody RegisterRequest request, HttpServletRequest httpServletRequest) {
        if (request.getIpAddress() == null || request.getIpAddress().isEmpty()) {
            request.setIpAddress(httpServletRequest.getRemoteAddr());
        }
        return userService.register(request);
    }

    @PostMapping("/login")
    public CommonResponse login(@RequestBody RegisterRequest request) {
        return userService.login(request);
    }
}
