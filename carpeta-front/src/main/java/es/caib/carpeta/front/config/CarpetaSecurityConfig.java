package es.caib.carpeta.front.config;

import es.caib.carpeta.front.security.CarpetaFrontAuthProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AndRequestMatcher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@ComponentScan("es.caib.carpeta.front")
public class CarpetaSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CarpetaFrontAuthProvider carpetaFrontAuthProvider;

    @Autowired
    private LoginRequestCache loginRequestCache;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(carpetaFrontAuthProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests().antMatchers("/","/inicio","/salir","/js/**","/css/**","/fonts/**","/files/**","/static/**","/accesibilidad", "/mapaWeb").permitAll()
                .and()
                .authorizeRequests().anyRequest().access("isAuthenticated()")
                .and()
                .csrf().requireCsrfProtectionMatcher(csrfMatcher())
                .and()
                .requestCache().requestCache(loginRequestCache)
                .and()
                .formLogin()
                .loginPage("/login")
                .failureForwardUrl("/login?error=true")
                .permitAll()
                .and().logout().logoutSuccessUrl("/salir");
    }

    @Bean
    AndRequestMatcher csrfMatcher(){
        AntPathRequestMatcher antPathRequestMatcher = new AntPathRequestMatcher("/**/*.json","POST");

        return new AndRequestMatcher(antPathRequestMatcher);
    }

}