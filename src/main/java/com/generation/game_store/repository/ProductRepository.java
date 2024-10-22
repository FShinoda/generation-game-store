package com.generation.game_store.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.generation.game_store.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
	public List<Product> findAllByNameContainingIgnoreCase(@Param("name") String name);
}
