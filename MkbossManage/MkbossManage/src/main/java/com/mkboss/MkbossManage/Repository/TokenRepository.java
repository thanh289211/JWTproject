package com.mkboss.MkbossManage.Repository;

import ch.qos.logback.core.testUtil.MockInitialContext;
import com.mkboss.MkbossManage.Entity.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByToken(String jwt);
    @Query(value = "select t from Token t inner join User u " +
            "on t.user.id = u.id " +
            "where u.id = :id and (t.expired = false or t.revoked = false)")
    List<Token> findAllValidTokensByUser(@Param("id") long id);
}
