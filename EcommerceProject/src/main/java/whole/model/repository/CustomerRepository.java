package whole.model.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import whole.model.entity.Cart;
import whole.model.entity.CompleteOrder;
import whole.model.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{
	
	@Query("SELECT c.cart FROM Customer c WHERE c.id = :customerId")
	public Cart getCartByCustomerId(@Param("customerId") long customerId);
	
	@Query("SELECT c.orders FROM Customer c WHERE c.id = :customerId")
	public List<CompleteOrder> getOrderHistory(@Param("customerId") long customerId);
	
}
