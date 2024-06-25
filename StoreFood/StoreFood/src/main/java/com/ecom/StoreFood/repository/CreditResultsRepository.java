package com.ecom.StoreFood.repository;

import com.ecom.StoreFood.entity.CreditResults;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CreditResultsRepository extends JpaRepository<CreditResults, Long> {

    Optional<CreditResults> findByDateCrediteId(Long creditId);

}
