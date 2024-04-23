package edu.tcu.cs.monnigmeteoritecatalog.security;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
@Configuration
public class SecurityConfiguration {

    @Value("${api.endpoint.base-url}")
    private String baseUrl;


    // No auth for find meteorites and view a meteorite
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .authorizeHttpRequests(authorizeHttpRequests -> authorizeHttpRequests
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/samples/view/**").permitAll() // all users can see all meteorites so permitAll
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/samples/all/**").permitAll() // all users can view a specific meteorite so permitAll
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/samples/add").hasAuthority("ROLE_admin") // only curator can add a meteorite to database
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/samples/update/**").hasAuthority("ROLE_admin") // only the curator can update a meteorite
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/samples/delete/**").hasAuthority("ROLE_admin") // only the curator can delete a meteorite
                        // add sub-sample
                        // view related samples
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/history/all/**").hasAuthority("ROLE_admin") // only the curator can see sample history
                        .requestMatchers(HttpMethod.DELETE, this.baseUrl + "/history/delete/**").hasAuthority("ROLE_admin") // only the curator can delete a history entry
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/loan/all").hasAuthority("ROLE_admin") // only the curator can view all loans
                        .requestMatchers(HttpMethod.GET, this.baseUrl + "/loan/view/**").hasAuthority("ROLE_admin") // only the curator can view a specific loan
                        // archive a loan
                        .requestMatchers(HttpMethod.POST, this.baseUrl + "/loan/create").hasAuthority("ROLE_admin") // only the curator can create a loan
                        .requestMatchers(HttpMethod.PUT, this.baseUrl + "/loan/update").hasAuthority("ROLE_admin") // only the curator can update a loan
                        // view all samples on loan
                        .requestMatchers(AntPathRequestMatcher.antMatcher("/h2-console/**")).permitAll()
                        .anyRequest().authenticated()
                )
                .headers(headers -> headers.frameOptions(Customizer.withDefaults()).disable()) // This is for H2 browser console access.
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

}
