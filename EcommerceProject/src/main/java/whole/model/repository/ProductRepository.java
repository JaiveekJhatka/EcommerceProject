package whole.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import whole.model.entity.Product;


@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	@Query("SELECT p FROM Product p JOIN p.category c WHERE c.name = :categoryName")
	List<Product> findProductsByCategory(@Param("categoryName") String categoryName);

	@Query("SELECT p FROM Product p WHERE p.name like %:productNameLike%")
	List<Product> findProductBySearchString(@Param("productNameLike") String productNameLike);

}
