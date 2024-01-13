package org.springboot.api.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info (title = "Spring Boot Open API Test with Swagger",
                description = "설명부분",
                    version = "1.0.0"))
@Configuration
public class SwaggerConfiguration {
    // http://localhost:8080/swagger-ui/index.html
}
