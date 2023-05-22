package com.mkboss.MkbossManage.Repository;

import com.mkboss.MkbossManage.Entity.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
}
