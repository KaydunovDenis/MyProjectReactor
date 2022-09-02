package com.github.kaydunovdenis.springsupplier.service;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.repository.SupplierRepository;
import com.github.kaydunovdenis.springsupplier.util.SupplierMapper;
import com.sun.istack.NotNull;
import org.mapstruct.factory.Mappers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

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

    public Supplier findById(@NonNull Long id) {
        Objects.requireNonNull(id);
        return supplierRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Supplier readExpandSupplier(Long id) {
        return supplierRepository.readExpandSupplier(id);
    }

    public List<Supplier> readByName(String name) {
        return supplierRepository.findByName(name);
    }

    @Transactional
    public List<Supplier> findAll() {
        return supplierRepository.findAll();
    }

    @Transactional
    public Supplier update(Supplier supplier) {
        Long id = getId(supplier);
        if (Objects.isNull(id)) {
            return save(supplier);
        }
        Supplier supplierDto = findById(id);
        SupplierMapper supplierMapper = Mappers.getMapper(SupplierMapper.class);
        supplierMapper.updateSupplierDto(supplier, supplierDto);
        return save(supplierDto);
    }

    public void deleteAll() {
        supplierRepository.deleteAll();
    }

    public void delete(Long id) {
        supplierRepository.deleteById(id);
    }

    private Long getId(Supplier supplier) {
        return Optional.of(supplier)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NO_CONTENT))
                .getId();
    }

}
