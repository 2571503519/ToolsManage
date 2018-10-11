package com.jack.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.support.StandardServletMultipartResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.io.IOException;

/**
 * @author JackarooZhang
 * @date 2018/6/18 10:32
 */
@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "com.jack.web")
@ImportResource({"classpath:spring-web.xml"})
public class WebConfig extends WebMvcConfigurerAdapter {

    /*配置所有未被Spring拦截的请求默认使用Servlet处理*/
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
        configurer.enable();
    }

    /*视图解析器*/
    @Bean
    public ViewResolver viewResolver() {
        // 配置视图解析器，这种视图解析器一般用于解析JSP
        InternalResourceViewResolver resolver =
                new InternalResourceViewResolver();
        // 设置视图解析器访问视图的前缀
        resolver.setPrefix("/WEB-INF/views/");
        // 后缀
        resolver.setSuffix(".jsp");
        // 设置是否所有的Spring上下文的Bean，都可以作为Request的属性，被Jstl访问到
        resolver.setExposeContextBeansAsAttributes(true);
        resolver.setViewClass(JstlView.class);
        return resolver;
    }

    // 配置处理Multipart文件的Bean
    @Bean(name = "multipartResolver")
    public MultipartResolver multipartResolver() throws IOException {
        /*高版本的Spring和Servlet容器中使用此方式，不需要导入第三方依赖*/
        StandardServletMultipartResolver resolver = new StandardServletMultipartResolver();
        return resolver;
    }


}
