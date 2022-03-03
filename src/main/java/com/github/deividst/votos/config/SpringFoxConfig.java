package com.github.deividst.votos.config;

import com.fasterxml.classmate.TypeResolver;
import com.github.deividst.votos.dtos.ErrorDataDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SpringFoxConfig implements WebMvcConfigurer {

    @Bean
    public Docket api() {
        TypeResolver typeResolver = new TypeResolver();

        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                    .apis(RequestHandlerSelectors.basePackage("com.github.deividst.votos.controller"))
                    .paths(PathSelectors.ant("/v1/*"))
                    .build()
                .additionalModels(typeResolver.resolve(ErrorDataDto.class))
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .tags(new Tag("Records", "Pauta"));
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("swagger-ui.html")
                .addResourceLocations("classpath:/META-INF/resources/");

        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Sessão de votação REST API")
                .description("Um aplicação Spring Boot REST API para gerenciar sessões de votação de uma assembleia")
                .version("1.0.0")
                .contact(new Contact("Deividst", "https://github.com/Deividst", "deivid.thome@outlook.com"))
                .build();
    }
}
