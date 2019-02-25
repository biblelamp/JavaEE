package eu.javageek;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.LdapShaPasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .anyRequest().fullyAuthenticated()
                .and()
            .formLogin();
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .ldapAuthentication()
                .userDnPatterns("uid={0},ou=users")
                //.groupSearchBase("ou=groups")
                .contextSource()
                    .url("ldap://localhost:389/dc=springframework,dc=org")
                    //.url("ldap://34.253.140.111:389/dc=educasoft,dc=local")
                    .and()
                .passwordCompare()
                    //.passwordEncoder(new LdapShaPasswordEncoder())
                    .passwordAttribute("userPassword");
    }

}
