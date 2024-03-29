package org.springboot.security.controller;

import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springboot.security.data.dto.SignInResultDto;
import org.springboot.security.data.dto.SignUpResultDto;
import org.springboot.security.service.SignService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/sign-api")
public class SignController {

    private final Logger LOGGER = LoggerFactory.getLogger(SignController.class);
    private final SignService signService;

    @PostMapping(value = "/sign-in")
    public SignInResultDto signIn(
            @ApiParam(value = "ID", required = true) @RequestParam String id,
            @ApiParam(value = "Password", required = true) @RequestParam String password) throws RuntimeException {
        LOGGER.info("[signIn] 로그인을 시도하고 있습니다. ID : {}, PW : ****", id);
        SignInResultDto signInResultDto = signService.signIn(id, password);

        if (signInResultDto.getCode() == 0) {
            LOGGER.info("[signIn] 정상적으로 로그인이 되었습니다. ID : {}, token : {}", id, signInResultDto.getToken());
        }

        return signInResultDto;
    }

    @PostMapping(value = "/sign-up")
    private SignUpResultDto signUp(
            @ApiParam(value = "ID", required = true) @RequestParam String id,
            @ApiParam(value = "PW", required = true) @RequestParam String password,
            @ApiParam(value = "이름", required = true) @RequestParam String name,
            @ApiParam(value = "권한", required = true) @RequestParam String role) {
        LOGGER.info("[signUp] 회원가입을 수행합니다. ID : {}, password : ****, name : {}, role : {}", id, name, role);
        SignUpResultDto signUpResultDto = signService.signUp(id, password, name, role);

        LOGGER.info("[signUp] 회원가입을 완료했습니다. ID : {}", id);

        return signUpResultDto;
    }

    @GetMapping(value = "/exception")
    public void exceptionTest() throws RuntimeException {
        throw new RuntimeException("접근이 금지되었습니다.");
    }

    @ExceptionHandler(value = RuntimeException.class)
    public ResponseEntity<Map<String, String>> ExceptionHandler(RuntimeException e) {
        HttpHeaders responseHeaders = new HttpHeaders();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        LOGGER.error("ExceptionHandler 호출, {}, {}", e.getCause(), e.getMessage());

        Map<String, String> map = new HashMap<>();
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", "400");
        map.put("message", "에러 발생");

        return new ResponseEntity<>(map, responseHeaders, httpStatus);
    }
}
