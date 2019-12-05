package station.check.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.check.pojo.InboundArrangement;
import station.users.pojo.Users;

public interface InboundArrangementRepository extends JpaRepository<InboundArrangement, Integer>{
	@Query("from InboundArrangement where check_user=? and inboundinspection=null")
	List<InboundArrangement> Oninboundbychecker(Users users);
}
