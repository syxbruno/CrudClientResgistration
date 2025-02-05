package com.syxbruno.CrudClientRegistration.config.swagger;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {
        final String securitySchemeName = "BearerAuth";
        return new OpenAPI()
                .info(new Info()
                        .title("Crud Client Registration")
                        .version("1.0")
                        .description("This project is a RESTful API developed with Spring Boot, focused on User and Client management. The application implements authentication via JWT and role-based access control, where users can have the admin or user roles. Persistence is performed in a MySQL database and the project has a robust suite of unit and integration tests, using H2 for simulations. In addition, the infrastructure is containerized with Docker, facilitating deployment and scalability of the environment. Use the login endpoint with the admin user and the admin password to obtain a token."))
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name("Bearer Authentication")
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")));
    }
}
