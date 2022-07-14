package com.github.kaydunovdenis.springsupplier.service;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.repository.SupplierRepository;
import com.github.kaydunovdenis.springsupplier.util.SupplierConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class SupplierService {

    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public Supplier save(Supplier supplier) {
        return supplierRepository.save(supplier);
    }

    public Supplier readById(Long id) {
        return supplierRepository.findById(id).orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Supplier readExpandSupplier(Long id) {
        return supplierRepository.readExpandSupplier(id);
    }

    public List<Supplier> readByName(String name) {
        return supplierRepository.findByName(name);
    }

    public List<Supplier> readAll() {
        return supplierRepository.findAll();
    }

    @Transactional
    public Supplier update(Supplier supplier, Long id) {
        Supplier supplierDTO = readById(id);

        //todo to interface
        //todo ConverterRegistry
        SupplierConverter.updateSupplier(supplier, supplierDTO);
        return save(supplierDTO);
    }

    public void delete(Supplier supplier) {
        supplierRepository.deleteById(supplier.getId());
    }

    public void deleteById(Long id) {
        supplierRepository.deleteById(id);
    }

}
