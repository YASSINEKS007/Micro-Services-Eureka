package me.projects.billingservice.web;

import lombok.AllArgsConstructor;
import me.projects.billingservice.entities.Bill;
import me.projects.billingservice.repositories.BillRepository;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin("*")
@AllArgsConstructor
public class BillRestController {

    private BillRepository billRepository;

    @GetMapping("/bills")
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }

}
