package com.github.kaydunovdenis.springsupplier.controller.controller0;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import com.github.kaydunovdenis.springsupplier.service.SupplierService;
import org.springframework.stereotype.Component;

@Component
public class DeleteRequestAction extends RequestAction{
    protected DeleteRequestAction(SupplierService supplierService) {
        super(supplierService);
    }

    @Override
    public Object request(Supplier supplier) {
        if (supplier == null) {
            supplierService.deleteAll();
        } else {
            supplierService.delete(supplier.getId());
        }
        return null;
    }
}
