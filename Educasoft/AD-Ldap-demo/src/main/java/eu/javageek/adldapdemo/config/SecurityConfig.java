package eu.javageek.adldapdemo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
public class SecurityConfig  extends WebSecurityConfigurerAdapter {

	@Value("${ldap.urls}")
	private String ldapUrls;

	@Value("${ldap.base.dn}")
	private String ldapBaseDn;

	@Value("${ldap.username}")
	private String ldapSecurityPrincipal;

	@Value("${ldap.password}")
	private String ldapPrincipalPassword;

	@Value("${ldap.user.dn.pattern}")
	private String ldapUserDnPattern;

	@Value("${ldap.enabled}")
	private String ldapEnabled;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http
        	.authorizeRequests()
        		.antMatchers("/login**").permitAll()
        		.antMatchers("/profile/**").fullyAuthenticated()
            	.antMatchers("/").permitAll()
            	.and()
            .formLogin()
            	.loginPage("/login")
            	.failureUrl("/login?error")
            	.permitAll()
            	.and()
            .logout()
            	.invalidateHttpSession(true)
            	.deleteCookies("JSESSIONID")
            	.permitAll();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		if (Boolean.parseBoolean(ldapEnabled)) {
			auth
				.ldapAuthentication()

//					.userDnPatterns("uid={0},ou=mathematicians")
//					.contextSource()
//						.url("ldap://ldap.forumsys.com:389/dc=example,dc=com")
//						.and()
//					.passwordCompare()
//						.passwordEncoder(new LdapShaPasswordEncoder())
//						.passwordAttribute("password");

				.contextSource()
					.url("ldap://ldap.forumsys.com:389/dc=example,dc=com")
					.managerDn("cn=read-only-admin,dc=example,dc=com")
					.managerPassword("password")
					.and()
					.userDnPatterns("uid={0}");
		} else {
	        auth
	        	.inMemoryAuthentication()
	            	.withUser("user").password("password").roles("USER")
	            	.and()
	            	.withUser("admin").password("admin").roles("ADMIN");
		}
	}
}
