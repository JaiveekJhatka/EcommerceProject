package whole.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import whole.model.entity.CompleteOrder;


@Repository
public interface CompleteOrderRepository extends JpaRepository<CompleteOrder, Long>{
	
}
