package be.edenglen.tournament.ws.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
//                .antMatchers("/", "/sponsors", "/help", "/editions", "/inscription", "/inscription/submit", "/static/**", "/webjars/**").permitAll()
//                .anyRequest().authenticated()
				.anyRequest().permitAll()
				.and()
				.formLogin()
				.loginPage("/login")
				.successForwardUrl("/admin")
				.permitAll()
				.and()
				.logout()
				.permitAll()
				.and()
				.headers().frameOptions().sameOrigin()
				.and()
				.csrf()
				.disable();
	}

	@Bean
	@Override
	protected UserDetailsService userDetailsService() {
		UserDetails user = User.withDefaultPasswordEncoder()
				.username("user")
				.password("password")
				.roles("USER")
				.build();

		return new InMemoryUserDetailsManager(user);
	}
}
