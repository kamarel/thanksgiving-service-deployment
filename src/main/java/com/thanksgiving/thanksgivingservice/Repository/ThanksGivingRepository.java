package com.thanksgiving.thanksgivingservice.Repository;

import com.thanksgiving.thanksgivingservice.Entity.ThanksGiving;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ThanksGivingRepository extends JpaRepository<ThanksGiving, Long> {
    @Query("SELECT p FROM ThanksGiving p WHERE " +
            "CAST(p.id AS string) LIKE CONCAT('%', :query, '%') " +
            "OR p.supervise LIKE CONCAT('%', :query, '%')")
    List<ThanksGiving> searchThanksGiving(@Param("query") String query);

    ThanksGiving findByThanksGivingId(String thanksGivingId);
}
