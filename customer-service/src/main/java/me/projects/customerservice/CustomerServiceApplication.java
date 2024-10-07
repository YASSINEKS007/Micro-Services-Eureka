package me.projects.customerservice;

import me.projects.customerservice.entities.Customer;
import me.projects.customerservice.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.stream.Stream;

@SpringBootApplication
public class CustomerServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CustomerServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner init(CustomerRepository customerRepository) {
        return args -> {
            Stream.of("Yassine", "Ilyas", "Yasmine").map(customerName -> {

                return Customer.builder()
                        .email(customerName + "@gmail.com")
                        .fullName(customerName)
                        .phone("0666666666")
                        .build();
            }).forEach(customerRepository::save);
        };
    }


}
