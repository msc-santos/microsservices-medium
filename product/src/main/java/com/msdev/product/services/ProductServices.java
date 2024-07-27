package com.msdev.product.services;

import com.msdev.product.entities.Product;
import com.msdev.product.repositories.ProductRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductServices {
  @Autowired
  private ProductRepository productRepository;

  public Page<Product> findAll(int page, int size) {
    return productRepository.findAll(PageRequest.of(page, size));
  }

  public Optional<Product> findById(Long productId) {
    return productRepository.findById(productId);
  }

}
