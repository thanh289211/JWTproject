package com.mkboss.MkbossManage.Repository;

import com.mkboss.MkbossManage.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
//    @Query(value = "select * from Product p " +
//            "where name like '%:partOfName%' " +
//            "and price >= :minPrice and price <= :maxPrice")
//    List<Product> search(@Param("partOfName") String partOfName,@Param("minPrice") double minPrice,@Param("maxPrice") double maxPrice);
}
