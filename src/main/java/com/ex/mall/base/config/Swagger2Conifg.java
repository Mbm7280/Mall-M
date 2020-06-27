package com.ex.mall.base.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
* @Package: com.ex.mall.base.config
* @ClassName: Swagger2Conifg
* @Description: config
 *              -- swagger2
* @Author: mbm
* @date: 2020/6/27 10:36
* @Version: 1.0
*/
@Configuration
@EnableSwagger2
public class Swagger2Conifg {

    @Bean
    public Docket createRestApi(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ex.mall.controller"))
                .paths(PathSelectors.any())
                .build()
                .securitySchemes(securitySchemes())
                .securityContexts(securityContexts());
    }

    public ApiInfo apiInfo(){
        return new ApiInfoBuilder()
                .title("Mall-Ex")
                .description("Ex")
                .contact("mall")
                .version("1.0")
                .build();
    }

    private List<ApiKey> securitySchemes(){
        List<ApiKey> result = new ArrayList<>();
        ApiKey apiKey = new ApiKey("Authorization","Authorization","header");
        result.add(apiKey);
        return result;
    }

    private List<SecurityReference> defaultPath(){
        List<SecurityReference> result = new ArrayList<>();
        AuthorizationScope authorizationScope = new AuthorizationScope("gloabl","accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        result.add(new SecurityReference("Authorization",authorizationScopes));
        return result;
    }

    private SecurityContext getContextByPath(String pathRegex){
        return SecurityContext.builder()
                .securityReferences(defaultPath())
                .forPaths(PathSelectors.regex(pathRegex))
                .build();
    }

    private List<SecurityContext> securityContexts(){
        List<SecurityContext> result = new ArrayList<>();
        result.add(getContextByPath("/brand/.*"));
        return result;
    }


}
