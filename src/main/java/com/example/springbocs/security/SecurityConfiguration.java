//package com.example.springbocs.security;
//
//import org.springframework.beans.factory.ObjectProvider;
//import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
//import org.springframework.boot.actuate.health.HealthEndpoint;
//import org.springframework.boot.actuate.info.InfoEndpoint;
//import org.springframework.boot.autoconfigure.security.SecurityProperties;
//import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration(proxyBeanMethods = false)
//@EnableWebSecurity
//public class SecurityConfiguration {
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http, SecurityProperties securityProperties) throws Exception {
//        return http
//                .authorizeHttpRequests(auth ->
//                        auth.requestMatchers(EndpointRequest.toAnyEndpoint().excluding(HealthEndpoint.class, InfoEndpoint.class))
//                                .hasAnyRole(securityProperties.getUser().getRoles().toArray(String[]::new))
//                                .anyRequest()
//                                .permitAll()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .csrf(AbstractHttpConfigurer::disable)
//                .build();
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService(
//            SecurityProperties securityProperties,
//            ObjectProvider<PasswordEncoder> passwordEncoder
//    ) {
//        return new UserDetailsServiceAutoConfiguration().inMemoryUserDetailsManager(securityProperties, passwordEncoder);
//    }
//}
