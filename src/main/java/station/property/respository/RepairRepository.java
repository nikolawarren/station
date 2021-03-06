package station.property.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.property.pojo.Repair;

public interface RepairRepository extends JpaRepository<Repair, Integer>{
	@Query("from Repair where approvaluser=null")
	List<Repair> showrepairNoApproval();
}
