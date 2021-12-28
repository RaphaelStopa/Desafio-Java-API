package com.example.demo.config;

import com.example.demo.service.impl.UserDetailsServiceImpl;
import com.example.demo.security.jwt.JwtFilter;
import com.example.demo.security.jwt.JwtProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.OncePerRequestFilter;

import static org.springframework.http.HttpMethod.OPTIONS;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private static final String ADMIN = "ADMIN";

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private JwtProvider jwtProvider;

    @Bean
    public OncePerRequestFilter jwtAuthenticationTokenFilter() {
        return new JwtFilter(this.userDetailsServiceImpl, this.jwtProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf()
                .disable()
                .authorizeRequests()
                .antMatchers("/api/authenticate/**").permitAll()
                .antMatchers("/api/register/**").permitAll()
                .antMatchers("/api/adresses/**").hasRole(ADMIN)
                .antMatchers("/api/cities/**").hasRole(ADMIN)
                .antMatchers("/api/law-projects/**").hasRole(ADMIN)
                .antMatchers("/api/phones/**").hasRole(ADMIN)
                .antMatchers("/api/photos/**").hasRole(ADMIN)
                .antMatchers("/api/political-parties/**").hasRole(ADMIN)
                .antMatchers("/api/processes/**").hasRole(ADMIN)
                .antMatchers("/api/states/**").hasRole(ADMIN)
                .antMatchers("/api/**").authenticated()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilterBefore(jwtAuthenticationTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers(OPTIONS, "/**")
                .antMatchers("/i18n/**")
                .antMatchers("/content/**")
                .antMatchers("/swagger-ui/**")
                .antMatchers("/v2/api-docs/**", "/configuration/ui",
                        "/swagger-resources/**", "/configuration/**", "/swagger-ui.html"
                        , "/webjars/**", "/csrf")
                .antMatchers("/test/**");
    }
}

