package org.springboot.valid_exception.data.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidRequestDto {

    @NotBlank
    String name;

    @Email
    String email;

    @Pattern(regexp = "01(?:0|1|[6-9])[.-]?(\\d{3}|\\d{4})[.-]?(\\d{4})$")
    String phoneNumber;

    @Min(value = 20)
    @Max(value = 40)
    int age;

    @Size(min = 0, max = 40)
    String description;

    @Positive // 해당 필드의 값은 양수 (0은 제외)
    int count;

    @AssertTrue // true 인지 체크. null 값은 체크x
    boolean booleanCheck;
}
