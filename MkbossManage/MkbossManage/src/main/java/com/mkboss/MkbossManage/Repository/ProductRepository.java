package com.mkboss.MkbossManage.Repository;

import com.mkboss.MkbossManage.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
