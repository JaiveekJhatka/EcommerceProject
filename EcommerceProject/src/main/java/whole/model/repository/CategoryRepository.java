package whole.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import whole.model.entity.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{
	
	public Optional<Category> findByName(String categoryName);
}
