package com.mani.fileservice;

import com.google.common.base.Predicates;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableScheduling
@ComponentScan({"com.mani.fileservice"})
@EnableJpaRepositories("com.mani.fileservice.persistence.db")
@EntityScan(basePackages = {"com.mani.fileservice.entity"})
@EnableSwagger2
public class FileserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FileserviceApplication.class, args);
    }

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .useDefaultResponseMessages(false)
                .apiInfo(apiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo apiInfo() {
        Contact contact = new Contact("Manikandan Jeyarajan", "https://github.com/manikandanj", "manikandan.jeyarajan@gmail.com");
        return new ApiInfoBuilder()
                .title("File Service")
                .description("Sample spring boot service to manage file upload")
                .contact(contact)
                .version("1.0")
                .build();
    }
}
