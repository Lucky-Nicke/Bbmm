package club.lanxige.bbmm.service;

import club.lanxige.bbmm.dto.LoginRequest;
import club.lanxige.bbmm.dto.LoginResponse;

public interface LoginService {
    LoginResponse login(LoginRequest request);
}