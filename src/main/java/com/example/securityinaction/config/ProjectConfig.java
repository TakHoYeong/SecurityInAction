package com.example.securityinaction.config;

import com.example.securityinaction.login.LoginFail;
import com.example.securityinaction.login.LoginSuccess;
import com.example.securityinaction.services.InMemoryUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectConfig {


    private final AuthenticationProvider authenticationProvider;

    private final LoginSuccess loginSuccess;

    private final LoginFail loginFail;

    public ProjectConfig(AuthenticationProvider authenticationProvider, LoginSuccess loginSuccess,
                         LoginFail loginFail) {
        this.authenticationProvider = authenticationProvider;
        this.loginSuccess = loginSuccess;
        this.loginFail = loginFail;
    }

    @Bean
    public UserDetailsService uds(){
        var uds = new InMemoryUserDetailsManager();

        uds.createUser(
                User.withDefaultPasswordEncoder()
                        .username("taco")
                        .password("12345")
                        .authorities("write")
                        .build()
        );

        return uds;
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http)throws Exception{
        http.formLogin(c ->
                c.successHandler(loginSuccess)
                        .failureHandler(loginFail));

        http.httpBasic(Customizer.withDefaults());

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        return http.build();
    }

    /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.httpBasic(Customizer.withDefaults());

        http.authenticationProvider(authenticationProvider);

        http.authorizeHttpRequests(c -> c.anyRequest().authenticated());

        return http.build();
    }

    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception{

        http.httpBasic(c -> {
            c.realmName("OTHER");
            c.authenticationEntryPoint(new CustomEntryPoint());
        });

        http.authorizeHttpRequests(c-> c.anyRequest().authenticated());

        return http.build();
    }*/




   /* @Bean
    public UserDetailsService userDetailsService(DataSource dataSource) {
        String usersByUsernameQuery = "select username, password, enabled from spring.users where username = ?";
        String authsByUserQuery = "select username, authority from spring.authorities where username = ?";
        var userDetailsManager = new JdbcUserDetailsManager(dataSource);
        userDetailsManager.setUsersByUsernameQuery(usersByUsernameQuery);
        userDetailsManager.setAuthoritiesByUsernameQuery(authsByUserQuery);
        return userDetailsManager;

    }*/


    // LDAP 사용
    /*@Bean
    public UserDetailsService userDetailsService() {
        var cs = new DefaultSpringSecurityContextSource(
                "ldap://127.0.0.1:33389/dc=springframework,dc=org");
        cs.afterPropertiesSet();
        var manager = new LdapUserDetailsManager(cs);
        manager.setUsernameMapper(
                new DefaultLdapUsernameToDnMapper("ou=groups", "uid"));
        manager.setGroupSearchBase("ou=groups");
        return manager;
    }*/


    /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }*/
}
