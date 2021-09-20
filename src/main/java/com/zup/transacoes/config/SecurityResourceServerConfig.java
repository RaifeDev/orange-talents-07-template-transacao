package com.zup.transacoes.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.oauth2.server.resource.OAuth2ResourceServerConfigurer;

@Configuration
@EnableWebSecurity
public class SecurityResourceServerConfig extends WebSecurityConfigurerAdapter {


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()
                .antMatchers(HttpMethod.GET, "/api/cartoes/**").hasAuthority("SCOPE_transacoes-scope")
                .antMatchers("/h2-console/**").permitAll()
                .anyRequest().authenticated();

        http.oauth2ResourceServer(OAuth2ResourceServerConfigurer::jwt);

        http.headers().frameOptions().sameOrigin()
                .and().csrf().disable();

    }



}
