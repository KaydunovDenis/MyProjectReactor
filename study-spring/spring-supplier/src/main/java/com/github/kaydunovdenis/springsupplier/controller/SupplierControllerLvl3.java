package com.github.kaydunovdenis.springsupplier.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * According to Richardson Maturity Model
 * Rest controller level 3 is
 * - many URI
 * - multiple HTTP methods
 * - resources describes own capabilities
 * - resources describes own interactions
 */
@RestController
@RequestMapping("/controller3")
public class SupplierControllerLvl3 {
}
