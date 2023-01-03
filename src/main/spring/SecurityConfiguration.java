package spring;

import enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity(debug = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter
{

    @SuppressWarnings("deprecation")
    @Bean
    public static NoOpPasswordEncoder passwordEncoder() {
        return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, "/login").hasAnyRole(Role.ADMIN.toString(), Role.USER.toString())
                .antMatchers(HttpMethod.GET, "/books").hasAnyRole(Role.ADMIN.toString(), Role.USER.toString())
                .antMatchers(HttpMethod.POST, "/book/add").hasAnyRole(Role.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/book/{bookId}/delete").hasAnyRole(Role.ADMIN.toString())
                .antMatchers(HttpMethod.PUT, "/book/{bookId}/longerDate").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE, "/book/{bookId}/user/{userId}").hasAnyRole(Role.USER.toString())
                .antMatchers(HttpMethod.POST, "/book/{bookId}/user/{userId}/borrow").hasAnyRole(Role.USER.toString())
                .antMatchers(HttpMethod.GET, "/books/{userId}").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
                .antMatchers(HttpMethod.GET, "/books/user/{userId}").hasAnyRole(Role.USER.toString(), Role.ADMIN.toString())
                .antMatchers(HttpMethod.GET,"/users").hasAnyRole(Role.ADMIN.toString())
                .antMatchers(HttpMethod.POST,"/user/add").hasAnyRole(Role.ADMIN.toString())
                .antMatchers(HttpMethod.DELETE,"/user/{userId}").hasAnyRole(Role.ADMIN.toString())
                .and()
                .httpBasic()
                .and()
                .cors()
                .and()
                .csrf()
                .disable();
    }

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

}
