package org.springboot.rest.controller;

import lombok.RequiredArgsConstructor;
import org.springboot.rest.dto.MemberDto;
import org.springboot.rest.service.WebClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/web-client")
public class WebClientController {

    private final WebClientService webClientService;

    @GetMapping
    public String getName() {
        return webClientService.getName();
    }

    @GetMapping("/path-variable")
    public String getNameWithPathVariable() {
        return webClientService.getNameWithPathVariable();
    }

    @GetMapping("/parameter")
    public String getNameWithParameter() {
        return webClientService.getNameWithParameter();
    }

    @PostMapping
    public ResponseEntity<MemberDto> postDto() {
        return webClientService.postWithParamAndBody();
    }

    @PostMapping("/header")
    public ResponseEntity<MemberDto> postWithHeader() {
        return webClientService.postWithHeader();
    }
}
