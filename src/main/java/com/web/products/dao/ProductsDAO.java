package com.web.products.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web.products.entitys.Product;

public interface ProductsDAO extends JpaRepository<Product, Long> {

}
