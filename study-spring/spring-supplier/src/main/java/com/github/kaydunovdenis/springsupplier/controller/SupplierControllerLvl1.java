package com.github.kaydunovdenis.springsupplier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * According to Richardson Maturity Model
 * Rest controller level 1 is
 * - many URI
 * - single HTTP method
 */
@RestController
@RequestMapping("/controller1")
public class SupplierControllerLvl1 {
}
