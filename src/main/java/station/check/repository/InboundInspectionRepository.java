package station.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import station.check.pojo.InboundInspection;

public interface InboundInspectionRepository extends JpaRepository<InboundInspection, Integer>{

}
