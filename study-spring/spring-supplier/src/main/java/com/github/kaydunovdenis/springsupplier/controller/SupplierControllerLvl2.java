package com.github.kaydunovdenis.springsupplier.controller;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * According to Richardson Maturity Model
 * Rest controller level 1 is
 * - many URI
 * - multiple HTTP methods
 *
 * We don't use a single POST method for all requests. We use the GET method when we request a resource,
 * and use the DELETE method when we want to delete a resource. Also, use the response codes of the application protocol.
 */
@RestController
@RequestMapping("/controller2")
public class SupplierControllerLvl2 {

    private final SupplierService supplierService;

    @Autowired
    public SupplierControllerLvl2(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping
    public ResponseEntity checkServer() {
        try {
            return ResponseEntity.ok("Server is running!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Server has error!");
        }
    }

    @PostMapping("/supplier")
    public Supplier save(@RequestBody Supplier supplier) {
        return supplierService.save(supplier);
    }

    @GetMapping("/supplier/{id}")
    public Supplier get(@PathVariable Long id) {
        return supplierService.findById(id);
    }

    @GetMapping("/supplier")
    public List<Supplier> getAll() {
        return supplierService.findAll();
    }

    @PutMapping("/supplier/{id}")
    public Supplier update(@RequestBody Supplier supplier, @PathVariable Long id) {
        return supplierService.update(supplier);
    }

    @DeleteMapping("/supplier/{id}")
    void delete(@PathVariable Long id) {
        supplierService.delete(id);
    }
}
