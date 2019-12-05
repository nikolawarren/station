package station.personal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.personal.pojo.RefundApply;
import station.personal.pojo.Ticket;

public interface RefundApplyRepository extends JpaRepository<RefundApply,Integer>{
	
	@Query("from RefundApply where check_user =null")
	List<RefundApply> findRefundAll();
}
