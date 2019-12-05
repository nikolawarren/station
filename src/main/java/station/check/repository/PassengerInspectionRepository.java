package station.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import station.check.pojo.PassengerInspection;

public interface PassengerInspectionRepository extends JpaRepository<PassengerInspection, Integer>{

}
