package spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages={"repositories"})
@ComponentScan({"services", "spring", "controllers", "models"})
@EntityScan(basePackages = {"entities"})
public class ApplicationStarter {
    public static void main(String[] args) {
        System.setProperty("server.servlet.context-path", "/Library");
        SpringApplication.run(ApplicationStarter.class, args);
        System.out.println("spring.ApplicationStarter has started");
    }
}
