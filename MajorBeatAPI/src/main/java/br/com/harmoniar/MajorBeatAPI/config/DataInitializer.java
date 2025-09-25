
package br.com.harmoniar.MajorBeatAPI.config;

import br.com.harmoniar.MajorBeatAPI.entity.Musico;
import br.com.harmoniar.MajorBeatAPI.enums.Role;
import br.com.harmoniar.MajorBeatAPI.repositories.MusicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class DataInitializer {

    @Autowired
    MusicoRepository repository;

    @Bean
    public CommandLineRunner initAdminUser(MusicoRepository repository, BCryptPasswordEncoder encoder) {
        return args -> {
            if (repository.findByEmail("admin@majorbeat.com").isEmpty()) {
                Musico admin = new Musico();
                admin.setNome("Admin");
                admin.setEmail("admin@majorbeat.com");
                admin.setSenha(encoder.encode("admin123"));
                admin.setRole(Role.ROLE_ADMIN);
                // Preencha os campos obrigatórios restantes ou deixe nulos, conforme seu modelo
                repository.save(admin);
                System.out.println("Usuário admin criado.");
            }
        };
    }
}
