package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.springframework.stereotype.Component;

@Component
public class PutRequestAction extends RequestAction{
    protected PutRequestAction(SupplierService supplierService) {
        super(supplierService);
    }

    @Override
    public Object request(Supplier supplier) {
        return supplierService.update(supplier);
    }
}
