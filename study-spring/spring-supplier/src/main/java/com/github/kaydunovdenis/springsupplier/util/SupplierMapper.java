package com.github.kaydunovdenis.springsupplier.util;

import com.github.kaydunovdenis.springsupplier.entity.Supplier;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

/**
 * The mapper based on MupStruct.
 *
 * We can trigger the MapStruct processing by executing 'mvn clean install'.
 * This will generate the implementation class under /target/generated-sources/annotations/.
 */
@Mapper
public interface SupplierMapper {
    @Mapping(target = "id", ignore = true)
    void updateSupplierDto(Supplier source, @MappingTarget Supplier target);
}
