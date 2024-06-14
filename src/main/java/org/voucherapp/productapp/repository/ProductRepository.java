package org.voucherapp.productapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.voucherapp.productapp.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product,Long> {
}
