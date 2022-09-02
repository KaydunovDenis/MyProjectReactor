package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.springframework.stereotype.Component;

@Component
public class GetRequestAction extends RequestAction {
    protected GetRequestAction(SupplierService supplierService) {
        super(supplierService);
    }

    @Override
    public Object request(Supplier supplier) {
        Object object;
        if (supplier == null) {
            object = supplierService.findAll();
        } else {
            object = supplierService.findById(supplier.getId());
        }
        return object;
    }
}
