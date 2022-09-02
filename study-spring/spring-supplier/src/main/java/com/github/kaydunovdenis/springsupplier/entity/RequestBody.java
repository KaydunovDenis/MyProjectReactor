package com.github.kaydunovdenis.springsupplier.entity;

import org.springframework.http.HttpMethod;

import java.util.List;

public class RequestBody {
    private HttpMethod method;
    private Supplier supplier;

    public RequestBody() {
    }

    public RequestBody(HttpMethod method, Supplier supplier) {
        this.method = method;
        this.supplier = supplier;
    }

    public HttpMethod getMethod() {
        return method;
    }

    public void setMethod(HttpMethod method) {
        this.method = method;
    }

    public Supplier getSupplier() {
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @Override
    public String toString() {
        return "RequestBody{" +
                "method=" + method +
                ", supplier=" + supplier +
                '}';
    }
}
