package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;

public abstract class RequestAction {

    protected final SupplierService supplierService;

    protected RequestAction(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    public abstract Object request(Supplier supplier);
}