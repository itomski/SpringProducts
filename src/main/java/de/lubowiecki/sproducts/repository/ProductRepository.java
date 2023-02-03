package de.lubowiecki.sproducts.repository;

import de.lubowiecki.sproducts.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
