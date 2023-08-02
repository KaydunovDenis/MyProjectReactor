package com.github.kaydunovdenis.openapi.api;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Optional;
@javax.annotation.Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2022-12-05T18:51:20.399676500+03:00[Europe/Minsk]")
@Controller
@RequestMapping("${openapi.swaggerPetstore.base-path:}")
public class PetsApiController implements PetsApi {

    private final PetsApiDelegate delegate;

    public PetsApiController(@org.springframework.beans.factory.annotation.Autowired(required = false) PetsApiDelegate delegate) {
        this.delegate = Optional.ofNullable(delegate).orElse(new PetsApiDelegate() {});
    }

    @Override
    public PetsApiDelegate getDelegate() {
        return delegate;
    }

}
