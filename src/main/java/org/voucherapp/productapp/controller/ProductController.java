package org.voucherapp.productapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.voucherapp.productapp.dto.VoucherDto;
import org.voucherapp.productapp.entity.Product;
import org.voucherapp.productapp.repository.ProductRepository;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductRepository productRepository;
    private final RestTemplate restTemplate;

    @Value("${voucher.service.url}")
    private String voucherServiceUrl;

    @Autowired
    public ProductController(ProductRepository productRepository, RestTemplate restTemplate) {
        this.productRepository = productRepository;
        this.restTemplate = restTemplate;
    }

    @PostMapping
    public Product createProduct(@RequestBody Product product) {
        VoucherDto voucher=restTemplate.getForObject(voucherServiceUrl+product.getVoucherCode(),VoucherDto.class);
        product.setPrice(product.getPrice().subtract(voucher.getDiscount()));
        return  productRepository.save(product);
    }
}
