package station.check.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.check.pojo.PassengerArrangement;
import station.users.pojo.Users;

public interface PassengerArrangementRepository extends JpaRepository<PassengerArrangement, Integer>{
	
	@Query("from PassengerArrangement where check_user=? and passengerinspection=null")
	List<PassengerArrangement> OnPassengerbychecker(Users users);
}
