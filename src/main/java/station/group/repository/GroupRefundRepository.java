package station.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.group.pojo.GroupRefund;

public interface GroupRefundRepository extends JpaRepository<GroupRefund, Integer>{
	@Query("from GroupRefund where check_user=null")
	List<GroupRefund> findRefundAll();
}
