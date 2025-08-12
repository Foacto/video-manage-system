package com.tuan.vtube.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class PropertyUtil {
    public static String SPRING_PROFILE;
    public static String SPRING_CLASS_LOADER;

    @Value("${spring.profiles.active}")
    public static void setSpringProfile(String springProfile) {
        SPRING_PROFILE = springProfile;
    }

    @Value("${spring.profiles.class-loader}")
    public static void setSpringClassLoader(String springClassLoader) {
        SPRING_CLASS_LOADER = springClassLoader;
    }
}
