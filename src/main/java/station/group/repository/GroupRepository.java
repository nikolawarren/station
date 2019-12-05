package station.group.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.group.pojo.GroupApply;
import station.property.pojo.Arrangement;
import station.users.pojo.Users;

public interface GroupRepository extends JpaRepository<GroupApply, Integer>{
	@Query("from GroupApply where apply_user=? and check_user=null")
	List<GroupApply> refundshow(Users userid);
	
	@Query("from GroupApply where check_user=null")
	List<GroupApply> groupAllshowoncheck();
	
	@Query("from GroupApply where check_user!=null and arrangement!=null and approval_user=null")
	List<GroupApply> groupAllshowonapproval();
	
	@Query("from GroupApply where check_user!=null and arrangement=null")
	List<GroupApply> groupAllshowonarrangement();
	
	@Query("from GroupApply where arrangement=?")
	GroupApply selectbyArrangement(Arrangement arrangement);
}
