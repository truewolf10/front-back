package com.packageBE.PMOStandard.security;

import com.packageBE.PMOStandard.service.PasswordEncoderFactory;
import com.packageBE.PMOStandard.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserService userService;

    @Bean
    public WebMvcConfigurer corsConfigurer(){
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                        .allowedHeaders("*")
                        .allowedOrigins("http://localhost:4200")
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
//                        .allowedOrigins("http://localhost:4200", "http://localhost:8080")
//                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS");
            }
        };
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(PasswordEncoderFactory.create());
    }

//    private PasswordEncoderFactory getPasswordEncoder() {
//        return new PasswordEncoderFactory() {
//            @Override
//            public String encode(CharSequence charSequence) {
//                return charSequence.toString();
//            }
//
//            @Override
//            public boolean matches(CharSequence rawPassword, String encodedPassword) {
//                return passwordEncoder().encode(rawPassword).equals(encodedPassword);
//            }
//        };
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and()
                .authorizeRequests()
                .antMatchers("/user/login","/logout","/register", "/units", "/user/activate**", "/user/forgetPassword",
                        "/user/changePassword").permitAll()
//                .antMatchers("/**").hasRole(UserRole.ADMIN.toString())
                //Role Admin can create/view/edit/delete project, phase, template
//                .antMatchers(HttpMethod.GET, "/project/**","/phase/**","/phase-template/**","/user/**").hasAnyRole(UserRole.ADMIN.toString(), UserRole.USER.toString(),UserRole.PROJECT_MANAGER.toString())
//                .antMatchers(HttpMethod.POST,"/project/**","/phase/**","/phase-template/**","/user/**").hasRole(UserRole.ADMIN.toString())
//                .antMatchers(HttpMethod.PUT,"/project/**","/phase/**","/phase-template/**","/user/**").hasRole(UserRole.ADMIN.toString())
//                .antMatchers(HttpMethod.DELETE,"/project/**","/phase/**","/phase-template/**","/user/**").hasRole(UserRole.ADMIN.toString())

//                //Role Project Manager can only Create/Update/Delete/View project
//
//               .antMatchers(HttpMethod.POST,"/project/**").hasRole(UserRole.PROJECT_MANAGER.toString())
//               .antMatchers(HttpMethod.PUT,"/project/**").hasRole(UserRole.PROJECT_MANAGER.toString())
//               .antMatchers(HttpMethod.DELETE,"/project/**").hasRole(UserRole.PROJECT_MANAGER.toString())
                .anyRequest().authenticated()
                .and()
                .logout().permitAll()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","POST"))
                .and()
                .httpBasic()
                .and()
                .formLogin().permitAll()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and().csrf().disable();
//                .and().csrf().ignoringAntMatchers("/logout").csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse());
    }

//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setPasswordEncoder(passwordEncoder());
//
//        return authProvider;
//    }
}