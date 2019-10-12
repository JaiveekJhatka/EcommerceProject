package whole.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import whole.model.entity.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long>{
	
	@Query("SELECT c FROM Cart c JOIN c.customer cust WHERE cust.id = :customerId")
	public Cart getCartByCustomerId(@Param("customerId") long customerId);
}
