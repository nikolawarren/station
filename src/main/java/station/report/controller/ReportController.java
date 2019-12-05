package station.report.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import station.report.pojo.Report;
import station.report.service.ReportService;

@Controller
@RequestMapping("/report")
public class ReportController{
	@Autowired
	private ReportService reportService;
	
	@RequestMapping("/{page}")
	public String page(@PathVariable String page,Model model) {
		List<String> listt=new ArrayList<String>();
		listt.add("资产负债表");
		listt.add("损益表");
		listt.add("现金流量表");
		listt.add("财务状况变动表");
		model.addAttribute("listt", listt);
		
		List<Report> listr=this.reportService.showreport();
		model.addAttribute("listr", listr);
		return "/report/"+page;
		
	}
	
	@RequestMapping("/selecttemplates")
	public String selecttemplates(Model model) {
		List<String> listt=new ArrayList<String>();
		listt.add("资产负债表");
		listt.add("损益表");
		listt.add("现金流量表");
		listt.add("财务状况变动表");
		model.addAttribute("listt", listt);
		
		return "/report/reportexport";
	}
	
	@RequestMapping("/middle")
	public String middle(HttpServletRequest request) {
		String type=request.getParameter("template");
		
		return "redirect:/report/export?type="+ type;
	}
	
	@RequestMapping("/export")
    public void export(HttpServletRequest request,HttpServletResponse response) throws Exception {
				this.reportService.export(request, response);

	}
	
	@RequestMapping("/addreport/{type}")
	@ResponseBody
	public String addreport(@RequestParam(name="file") MultipartFile file,@PathVariable String type)  {
		
		try {
			this.reportService.addreport(file,type);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return"1";
	}
	
	@RequestMapping("/showreport")
	public String showreport(Model model) {
		List<String> listt=new ArrayList<String>();
		listt.add("资产负债表");
		listt.add("损益表");
		listt.add("现金流量表");
		listt.add("财务状况变动表");
		model.addAttribute("listt", listt);
		
		List<Report> listr=this.reportService.showreport();
		model.addAttribute("listr", listr);
		return "/report/report";
		
	}
	
	@RequestMapping("/download")
	public void download(String file,HttpServletResponse response,HttpServletRequest request) {
		Integer report_id=Integer.valueOf(request.getParameter("report_id"));
		try {
			this.reportService.download(request, response, report_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	@RequestMapping("/ondelete")
	@ResponseBody
	public String delete(HttpServletResponse response,HttpServletRequest request) {
		
		Integer report_id=Integer.valueOf(request.getParameter("report_id"));
		try {
			this.reportService.delete(report_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1";
	}
	
	@RequestMapping("/Query/{page}")
	public String Query(HttpServletRequest request,Model model,@PathVariable String page) {
		Integer report_id;
		if(request.getParameter("report_id")==null||request.getParameter("report_id").isEmpty()) {
			report_id=null;
		}
		else {
			 report_id=Integer.valueOf(request.getParameter("report_id"));
		}
		String report_name=request.getParameter("report_name");
		String type=request.getParameter("type");
		
		List<Report> listr=this.reportService.conditionalquery(report_id, type, report_name);
		if(model.containsAttribute("listr")) {
			System.out.print("shide");
		}
		List<String> listt=new ArrayList<String>();
		listt.add("资产负债表");
		listt.add("损益表");
		listt.add("现金流量表");
		listt.add("财务状况变动表");
		model.addAttribute("listt", listt);
		model.addAttribute("listr",listr);
		return "/report/"+page;
	}
	
	@RequestMapping("/update/{id}")
	public String  update(Model model,@PathVariable Integer id) {
		Integer report_id=id;
		model.addAttribute("report_id", report_id);
		return "/report/updatereport";
		
	}
	
	@RequestMapping("/onupdate/{id}")
	@ResponseBody
	public String onupdate(@RequestParam(name="file") MultipartFile file,@PathVariable String id) {
		Integer report_id=Integer.valueOf(id);
		try {
			this.reportService.update(file, report_id);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "1";
	}
	
}
	

