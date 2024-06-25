package com.ecom.StoreFood.services.auth;

import com.ecom.StoreFood.dto.SignupRequest;
import com.ecom.StoreFood.dto.UserDto;

public interface AuthService {
    UserDto createUser(SignupRequest signupRequest);
     Boolean hasUserWithEmail (String email);
    UserDto updateCreditLimit(Long userId, Double newCreditLimit);

}
