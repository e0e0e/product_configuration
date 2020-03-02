package pl.sda.pms.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebSecurity
class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    private final DbUserDetailsService dbUserDetailsService;

    public SecurityConfig(DbUserDetailsService dbUserDetailsService) {
        this.dbUserDetailsService = dbUserDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.userDetailsService(dbUserDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .mvcMatchers("/users").permitAll()
                .mvcMatchers("/users/list").hasAuthority("ADMIN")
                .mvcMatchers("/auth/**").hasAuthority("ADMIN")
               .mvcMatchers("/h2/**").permitAll()
                .mvcMatchers("/**").authenticated()
//            .mvcMatchers("/project/**").authenticated()
//            .mvcMatchers("/h2/**").permitAll()
                .anyRequest().permitAll()
                .and()
                .formLogin()
                .loginPage("/my-login")
                .permitAll(true)
                .and()
                .logout().deleteCookies("JSESSIONID")

                .logoutSuccessUrl("/")

                .and()
                .rememberMe().key("uniqueAndSecret").tokenValiditySeconds(86400)

                .and()
                .exceptionHandling().accessDeniedPage("/accessDenied");

        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
    @Bean
    WebMvcConfigurer myWebMvcConfigurer(){

        return new WebMvcConfigurerAdapter() {
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {


                ViewControllerRegistration r=registry.addViewController("/my-login");
                r.setViewName("user/pmsLogin");
            }
        };
    }
}
