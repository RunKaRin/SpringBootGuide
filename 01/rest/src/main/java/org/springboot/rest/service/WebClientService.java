package org.springboot.rest.service;

import org.springboot.rest.dto.MemberDto;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class WebClientService {

    // GET 요청
    public String getName() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        return webClient.get()
                .uri("crud-api")
                .retrieve()
                .bodyToMono(String.class)
                .block();
    }

    public String getNameWithPathVariable() {
        WebClient webClient = WebClient.create("http://localhost:9000");

        ResponseEntity<String> responseEntity = webClient.get()
                .uri(uriBuilder -> uriBuilder.path("crud-api/{name}")
                        .build("KaRin"))
                .retrieve()
                .toEntity(String.class)
                .block();

        return responseEntity.getBody();
    }

    public String getNameWithParameter() {
        WebClient webClient = WebClient.create("http://localhost:9000");

        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/crud-api")
                .queryParam("name", "KaRin")
                .build())
                .exchangeToMono(clientResponse -> {
                    if (clientResponse.statusCode().equals(HttpStatus.OK)) {
                        return clientResponse.bodyToMono(String.class);
                    } else {
                        return clientResponse.createException().flatMap(Mono::error);
                    }
                })
                .block();
    }


    // POST 요청
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("KaRin");
        memberDto.setEmail("test@test.com");
        memberDto.setOrganization("Test Test");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("/crud-api")
                        .queryParam("name", "KaRin")
                        .queryParam("email", "test@test.com")
                        .queryParam("organization", "Test Test")
                        .build())
                .bodyValue(memberDto)
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }

    public ResponseEntity<MemberDto> postWithHeader() {
        WebClient webClient = WebClient.builder()
                .baseUrl("http://localhost:9000")
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("KaRin");
        memberDto.setEmail("test@test.com");
        memberDto.setOrganization("Test Test");

        return webClient.post()
                .uri(uriBuilder -> uriBuilder.path("crud-api/add-header")
                        .build())
                .bodyValue(memberDto)
                .header("my-header", "Test API")
                .retrieve()
                .toEntity(MemberDto.class)
                .block();
    }
}
