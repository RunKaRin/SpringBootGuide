package org.springboot.security.service;

import org.springboot.security.data.dto.SignInResultDto;
import org.springboot.security.data.dto.SignUpResultDto;

public interface SignService {

    SignUpResultDto signUp(String id, String password, String name, String role);

    SignInResultDto signIn(String id, String password) throws RuntimeException;
}
