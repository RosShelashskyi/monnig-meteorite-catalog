package edu.tcu.cs.monnigmeteoritecatalog;

import edu.tcu.cs.monnigmeteoritecatalog.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MonnigMeteoriteCatalogApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonnigMeteoriteCatalogApplication.class, args);
    }

    @Bean
    public IdWorker idWorker(){
        return new IdWorker(1, 1);
    }
}
