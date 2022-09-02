package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.springframework.stereotype.Component;

@Component
public class PostRequestAction extends RequestAction{
    protected PostRequestAction(SupplierService supplierService) {
        super(supplierService);
    }

    @Override
    public Object request(Supplier supplier) {
        return supplierService.save(supplier);
    }
}
