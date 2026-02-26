package br.com.harmoniar.MajorBeatAPI.config;

import br.com.harmoniar.MajorBeatAPI.utils.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .cors(cors -> {}) // <<< usa configuração de CORS
                .authorizeHttpRequests(auth -> auth
                        // libera OPTIONS para pré-flight (IMPORTANTÍSSIMO)
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()

                        // rotas públicas
                        .requestMatchers(
                                "/Musico/login", "/Musico/cadastrar", "/Musico/uploadTemp", "/Musico/uploadTempMulti",
                                "/Contratante/login", "/Contratante/cadastrar", "/Contratante/uploadTemp",
                                "/Contratante/uploadMedia", "/Musico/uploadTemp", "/Musico/uploadMedia"
                        ).permitAll()

                        // daqui para baixo só autenticado
                        .requestMatchers("/Proposta/**").authenticated()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
