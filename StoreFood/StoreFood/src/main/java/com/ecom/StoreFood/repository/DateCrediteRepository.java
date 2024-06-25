package com.ecom.StoreFood.repository;

import com.ecom.StoreFood.entity.DateCredite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Repository
public interface DateCrediteRepository extends JpaRepository<DateCredite, Integer> {

    CompletableFuture<List<DateCredite>> findAllByUserId(int userId);

}
