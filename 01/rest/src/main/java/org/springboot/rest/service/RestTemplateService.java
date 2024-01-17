package org.springboot.rest.service;

import org.springboot.rest.dto.MemberDto;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class RestTemplateService {

    // GET 형식
    public String getName() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/crud-api")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public String getNameWithPathVariable() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("/crud-api/{name}")
                .encode()
                .build()
                .expand("KaRin")
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }

    public String getNameWithParameter() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("crud-api/param")
                .queryParam("name", "KaRin")
                .encode()
                .build()
                .toUri();

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(uri, String.class);

        return responseEntity.getBody();
    }


    // POST 형식
    public ResponseEntity<MemberDto> postWithParamAndBody() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("crud-api")
                .queryParam("name", "KaRin")
                .queryParam("email", "test@test.com")
                .queryParam("organization", "Test")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("KaRin");
        memberDto.setEmail("test@test.com");
        memberDto.setOrganization("Test Test");

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.postForEntity(uri, memberDto, MemberDto.class);

        return responseEntity;
    }

    public ResponseEntity<MemberDto> postWithHeader() {
        URI uri = UriComponentsBuilder
                .fromUriString("http://localhost:9000")
                .path("crud-api/add-header")
                .encode()
                .build()
                .toUri();

        MemberDto memberDto = new MemberDto();
        memberDto.setName("KaRin");
        memberDto.setEmail("test@test.com");
        memberDto.setOrganization("Test Test");

        RequestEntity<MemberDto> requestEntity = RequestEntity
                .post(uri)
                .header("my-header", "Test API")
                .body(memberDto);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<MemberDto> responseEntity = restTemplate.exchange(requestEntity, MemberDto.class);

        return responseEntity;
    }
}
