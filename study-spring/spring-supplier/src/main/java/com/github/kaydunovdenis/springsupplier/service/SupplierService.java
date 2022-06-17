package com.github.kaydunovdenis.springsupplier.service;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.repository.SupplierRepository;
import com.github.kaydunovdenis.springsupplier.util.SupplierConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier create(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier read(Long id) {
        return supplierRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Supplier readByName(String name) {
        return supplierRepository.findByName(name).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public List<Supplier> readAll() {
        return supplierRepository.findAll();
    }

    public Supplier update(Supplier supplier, Long id) {
        Supplier supplierToUpdate = read(id);
        SupplierConverter.updateSupplier(supplier, supplierToUpdate);
        return supplierRepository.save(supplierToUpdate);
    }

    public Supplier update(Supplier supplier) {
        return update(supplier, supplier.getId());
    }

    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }
}
