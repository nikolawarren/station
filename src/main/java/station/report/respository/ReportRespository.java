package station.report.respository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import station.report.pojo.Report;

public interface ReportRespository extends JpaRepository<Report, Integer>,JpaSpecificationExecutor<Report>{
	
}
