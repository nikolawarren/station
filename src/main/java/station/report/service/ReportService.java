package station.report.service;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import station.report.pojo.Report;

public interface ReportService {
	void export(HttpServletRequest request,HttpServletResponse response);
	
	void addreport(MultipartFile file,String type) throws IOException;
	
	List<Report> showreport();
	
	void download(HttpServletRequest request,HttpServletResponse response,Integer report_id) throws IOException;
	
	List<Report> conditionalquery(Integer report_id,String type,String report_name);
	
	void delete(Integer report_id)  throws IOException;
	
	void update(MultipartFile file,Integer report_id) throws IOException;
}
