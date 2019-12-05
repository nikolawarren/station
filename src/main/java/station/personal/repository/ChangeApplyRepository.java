package station.personal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.personal.pojo.ChangeApply;




public interface ChangeApplyRepository extends JpaRepository<ChangeApply, Integer>{
	@Query("from ChangeApply where check_user =null")
	List<ChangeApply> findChangeAll();
}
