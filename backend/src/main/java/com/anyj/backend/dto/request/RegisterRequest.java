package com.anyj.backend.dto.request;

import lombok.Data;

@Data
public class RegisterRequest {
    private String username;
    private String password;
    private String phoneNumber;
    private String ipAddress;
}
