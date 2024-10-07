package me.projects.billingservice;

import me.projects.billingservice.entities.Bill;
import me.projects.billingservice.entities.ProductItem;
import me.projects.billingservice.models.Customer;
import me.projects.billingservice.models.Product;
import me.projects.billingservice.repositories.BillRepository;
import me.projects.billingservice.repositories.ProductItemRepository;
import me.projects.billingservice.services.CustomerRestClient;
import me.projects.billingservice.services.ProductRestClient;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@EnableFeignClients
public class BillingServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BillingServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(CustomerRestClient customerRestClient,
                                               ProductRestClient productRestClient,
                                               ProductItemRepository productItemRepository,
                                               BillRepository billRepository) {
        return args -> {
            List<Customer> customers = customerRestClient.findCustomers();
            List<Product> products = productRestClient.findAllProducts();

            for (Customer customer : customers) {
                // Create a new bill instance and set its properties
                Bill bill = new Bill();
                bill.setCustomer(customer);
                bill.setBillingDate(new Date());
                bill.setCustomerId(customer.getId());

                // Save the bill first to get an ID
                bill = billRepository.save(bill); // Persist the bill to the database

                // Now create product items associated with the saved bill
                List<ProductItem> productItems = new ArrayList<>();
                for (Product product : products) {
                    ProductItem productItem = new ProductItem();
                    productItem.setProductId(product.getId());
                    productItem.setBill(bill); // Set the saved bill
                    productItem.setProduct(product);
                    productItem.setQuantity(1);
                    productItem.setDiscount(Math.random() * 100);
                    productItem.setPrice(Math.random() * 1000);
                    productItems.add(productItem);
                }
                productItemRepository.saveAll(productItems); // Now save all product items
            }
        };
    }

}
