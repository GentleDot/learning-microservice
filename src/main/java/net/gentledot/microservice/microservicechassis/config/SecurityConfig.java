package net.gentledot.microservice.microservicechassis.config;

import net.gentledot.microservice.microservicechassis.filter.CheckEmailFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationEntryPoint authEntryPoint;

    public SecurityConfig(AuthenticationEntryPoint authEntryPoint) {
        this.authEntryPoint = authEntryPoint;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests().antMatchers("/api/chassis/security/basic_auth/*").authenticated()
                .anyRequest().permitAll().and().httpBasic().authenticationEntryPoint(authEntryPoint).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("admin").password(encoder().encode("password")).roles("ADMIN");
        auth.inMemoryAuthentication().withUser("spiderman").password(encoder().encode("spidey")).roles("SUPERHERO");
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public FilterRegistrationBean<CheckEmailFilter> checkEmailFilterRegistration() {
        FilterRegistrationBean<CheckEmailFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(new CheckEmailFilter());
        registrationBean.addUrlPatterns("/api/chassis/security/email/*");

        return registrationBean;
    }
}
