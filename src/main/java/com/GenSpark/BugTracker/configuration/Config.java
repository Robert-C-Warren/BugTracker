package com.GenSpark.BugTracker.configuration;

import com.GenSpark.BugTracker.repository.CustomDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class Config extends WebSecurityConfigurerAdapter {
    @Autowired
    private CustomDetailService userDetailService;
    @Autowired
    private JWTTokenHelper jwtTokenHelper;
    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailService);
        provider.setPasswordEncoder(new BCryptPasswordEncoder());
        return provider;
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManager() throws Exception{
        return super.authenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /*
        http.authorizeHttpRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/userregister").permitAll()
                .antMatchers("/user").hasAnyAuthority("user", "admin")
                .antMatchers("/bugs").hasAnyAuthority("user", "admin")
                .antMatchers("/admin").hasAuthority("admin")
                .antMatchers("/bug").hasAuthority("admin")
                .antMatchers("/bug/{bugId}").hasAuthority("admin")
                .anyRequest().authenticated()
                .and()
                .httpBasic();

         */

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().exceptionHandling()
                        .authenticationEntryPoint(authenticationEntryPoint).and()
                        .authorizeRequests((request) -> request.antMatchers("/").permitAll()
                                                                .antMatchers("/userregister").permitAll()
                                                                .antMatchers("/auth/login").permitAll()
                                                                .antMatchers("/user").hasAnyAuthority("user", "admin")
                                                                .antMatchers("/bugs").hasAnyAuthority("user", "admin")
                                                                .antMatchers("/admin").hasAuthority("admin")
                                                                .antMatchers("/bug").hasAuthority("admin")
                                                                .antMatchers("/bug/{bugId}").hasAuthority("admin")
                                                                .anyRequest().authenticated())
                                .addFilterBefore(new JWTAuthenticationFilter(userDetailService, jwtTokenHelper), UsernamePasswordAuthenticationFilter.class);

        http.csrf().disable();
    }


}
