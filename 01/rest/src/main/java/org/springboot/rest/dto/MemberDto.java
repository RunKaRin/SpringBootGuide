package org.springboot.rest.dto;

import lombok.Data;

@Data
public class MemberDto {

    private String name;
    private String email;
    private String organization;
}
