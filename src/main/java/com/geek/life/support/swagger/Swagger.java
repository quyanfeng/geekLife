package com.geek.life.support.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.ArrayList;
import java.util.List;

/**
 * SWAGGER_2 API接口文档配置文件
 * 访问地址：http://localhost:8082/swagger-ui.html#!
 * @author qyf
 */
@Configuration
public class Swagger {

    /**
     * 当前环境是否可访问swagger
     **/
    @Value("${swagger.show}")
    private boolean swaggerShow;

    @Bean
    public Docket createRestApi() {
        ParameterBuilder parameterBuilder = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        parameterBuilder.name("token").description("令牌：用户登陆成功后需传").modelRef(new ModelRef("string")).
                parameterType("header").required(false).build();
        pars.add(parameterBuilder.build());


        ParameterBuilder parameterBuilder2 = new ParameterBuilder();
        parameterBuilder2.name("device").description("device：设备类型 0Web,1Andriod,2Ios,3H5").modelRef(new ModelRef("int")).
                parameterType("header").required(false).build();
        pars.add(parameterBuilder2.build());

        return new Docket(DocumentationType.SWAGGER_2)
                .enable(swaggerShow)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.geek.life"))
                .paths(PathSelectors.any())
                .build()
                .globalOperationParameters(pars)
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("极客人生- demo模块 - api文档")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0.0")
                .build();
    }
}
