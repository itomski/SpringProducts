package de.lubowiecki.sproducts.repository;

import de.lubowiecki.sproducts.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
