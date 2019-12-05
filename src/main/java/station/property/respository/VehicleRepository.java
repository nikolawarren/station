package station.property.respository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.property.pojo.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle,Integer>{
	@Query("from Vehicle where state=null")
	List<Vehicle> showVehicleNoState();
}
