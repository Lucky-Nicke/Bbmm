package club.lanxige.bbmm.service;

import club.lanxige.bbmm.dto.ApiResponse;
import club.lanxige.bbmm.dto.RegisterRequest;

public interface AccountService {
    ApiResponse<?> register(RegisterRequest request);
}
