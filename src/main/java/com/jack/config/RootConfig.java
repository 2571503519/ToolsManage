package com.jack.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * @author JackarooZhang
 * @date 2018/6/18 10:32
 */
@Configuration
/*设置扫描的包，将过滤带有EnableWebMvc注解的*/
@ComponentScan(basePackages = "com.jack",
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
@MapperScan(basePackages = "com.jack.dao")
@PropertySource(value = {"classpath:datasource.properties"})
/*引入XML配置，可以传入多个文件位置*/
@ImportResource({"classpath:spring-dao.xml"})
public class RootConfig {

    @Bean
    public static PropertySourcesPlaceholderConfigurer placeholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
