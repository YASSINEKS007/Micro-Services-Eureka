package me.projects.inventoryservice.web;

import lombok.AllArgsConstructor;
import me.projects.inventoryservice.entities.Product;
import me.projects.inventoryservice.repositories.ProductRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class ProductRestController {
    private ProductRepository productRepository;

    @GetMapping("/products")
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @GetMapping("/products/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElse(null);
    }

    @PostMapping("/save-product")
    public Product saveProduct(@RequestBody Product product) {
        return productRepository.save(product);
    }

    

}
