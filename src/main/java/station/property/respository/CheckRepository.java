package station.property.respository;

import org.springframework.data.jpa.repository.JpaRepository;

import station.property.pojo.Check;

public interface CheckRepository extends JpaRepository<Check, Integer>{

}
