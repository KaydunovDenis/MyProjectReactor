package com.github.kaydunovdenis.springsupplier.controller;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@RestController
public class SupplierControllerLevelZero {

    private final SupplierService supplierService;

    @Autowired
    public SupplierControllerLevelZero(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/supplier")
    public List<Supplier> getAll() {
        return supplierService.readAll();
    }

    @PostMapping("/supplier")
    public Supplier newSupplier(@RequestBody Supplier newEmployee) {
        return supplierService.save(newEmployee);
    }

    @GetMapping("/supplier/{id}")
    public Supplier getSupplier(@PathVariable Long id) {
        return supplierService.readById(id);
    }

    @PutMapping("/supplier/{id}")
    public Supplier replaceSupplier(@RequestBody Supplier supplier, @PathVariable Long id) {
        return supplierService.update(supplier, id);
    }

    @DeleteMapping("/supplier/{id}")
    void deleteSupplier(@PathVariable Long id) {
        supplierService.deleteById(id);
    }
}
