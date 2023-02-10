package com.example.NewJeans.interceptor;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class configure implements WebMvcConfigurer {
    private final Inteceptor interceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
                //.addPathPatterns() 인터셉터 적용될 url
                //.addPathPatterns()
                //.excludePathPatterns(); 인터셉터 동작하지 않을 때 url

    }
}

//@Configuration
//@RequiredArgsConstructor
//public class configure implements WebMvcConfigurer {
//    private final Interceptor interceptor;
//
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(interceptor);
//        //.addPathPatterns() 인터셉터 적용될 url
//        //.addPathPatterns()
//        //.excludePathPatterns(); 인터셉터 동작하지 않을 때 url
//
//    }
//}

