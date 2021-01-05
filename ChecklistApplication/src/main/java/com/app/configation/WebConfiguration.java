package com.app.configation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.app.interceptor.RequestInterceptor;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class WebConfiguration implements WebMvcConfigurer {

	@Autowired
	private RequestInterceptor crudRequestInterceptor;
	
	@Bean
    public Docket productApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.app.module"))
                .paths(PathSelectors.any())
                .build()
                .apiInfo(metaData());
    }
    private ApiInfo metaData() {
        return new ApiInfoBuilder()
                .title("Checklist Application")
                .description("Checklist service")
                .version("1.0.0")
                .license("Apache License Version 2.0")
                .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0\"")
                .contact(new Contact("","", ""))
                .build();
    }
    @Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	registry.addResourceHandler("swagger-ui.html")
    		.addResourceLocations("classpath:/META-INF/resources/");
    	
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }
    
    @Override
	public void addInterceptors(InterceptorRegistry registry) {
	    List<String> excludeApis = new ArrayList<>();
	    excludeApis.add("/**/login");
	    excludeApis.add("/**/insertOwner");
	    excludeApis.add("/**/insertEmployee");
	    excludeApis.add("/**/swagger-ui.html");
	    excludeApis.add("/**/webjars/springfox-swagger-ui/**");
	    excludeApis.add("/**/swagger-resources/**");
	    excludeApis.add("/**/v2/api-docs/**");    
	   // registry.addInterceptor(crudRequestInterceptor).addPathPatterns("/**").excludePathPatterns(excludeApis);
		
	}
	
}
