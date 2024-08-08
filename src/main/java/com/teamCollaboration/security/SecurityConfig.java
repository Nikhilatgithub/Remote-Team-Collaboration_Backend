package com.teamCollaboration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

//    private final JwtTokenProvider jwtTokenProvider;
	@Autowired
    private UserDetailsServiceImpl userDetailsService;

//    @Bean
//    public JwtTokenFilter jwtTokenFilter() {
//        return new JwtTokenFilter(jwtTokenProvider, userDetailsService);
//    }
	
	@Autowired
	private JwtAuthenticationFilter authenticationFilter;
	
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception
	{
		return super.authenticationManagerBean();
	}

    public UserDetailsService getUserDetailsService()
    {
    	return new UserDetailsServiceImpl();
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    
    
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .cors(cors -> cors.disable())
                .sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests(requests -> requests
                        .antMatchers("/auth/**").permitAll() //login and registration made public accessible
                        .antMatchers("/employee/**").hasRole("EMPLOYEE")// employee can access only employee urls
                        .antMatchers("/manager/**").hasRole("MANAGER")//manager side 
                        .antMatchers("/admin/**").hasRole("ADMIN")//admin side 
                        .anyRequest().authenticated())
                     .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}