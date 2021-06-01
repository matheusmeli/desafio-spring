package br.com.mercadolivre.desafiospring.config;

import br.com.mercadolivre.desafiospring.services.DBService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("dev")
public class DevConfig {

    private final DBService dbService;

    public DevConfig(DBService dbService) {
        this.dbService = dbService;
    }

    @Bean
    public boolean instantiateDatabase(){
        dbService.instantiateTestDatabase();
        return true;
    }
}
