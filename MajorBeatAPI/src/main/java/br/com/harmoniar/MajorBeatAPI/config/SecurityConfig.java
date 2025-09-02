package br.com.harmoniar.MajorBeatAPI.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // se não usar CSRF (API REST)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/musicos/cadastrar").permitAll() // liberar cadastro público
                        .anyRequest().authenticated()
                )
                .httpBasic(withDefaults()); // habilita autenticação básica

        return http.build();
    }
}
