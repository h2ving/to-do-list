package com.sda.todolist.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    CustomLogoutHandler customLogoutHandler;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {

/*** For usage in memory authentication
        auth.inMemoryAuthentication()
                .withUser("h2ving")
                .password(passwordEncoder().encode("qwerty"))
                .roles("USER")
                .and()
                .withUser("spring")
                .password(passwordEncoder().encode("password"))
                .roles("USER");
 */

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("SELECT email, password, enabled FROM user WHERE email=?")
                .authoritiesByUsernameQuery("select email, authority from authorities where email=?");
    }


    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/api/**").permitAll()
                .anyRequest().authenticated()
                .and()
                .httpBasic()
                .and()
                .formLogin().loginPage("/user/login")
                .defaultSuccessUrl("/", true)
                .failureHandler(authenticationFailureHandler())
                .and()
                .logout()
                .logoutUrl("/user/logout")
                .addLogoutHandler(customLogoutHandler);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new CustomAuthenticationFailure();
    }
}
