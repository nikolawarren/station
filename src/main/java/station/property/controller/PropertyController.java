package station.property.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import station.group.pojo.GroupApply;
import station.property.pojo.Arrangement;
import station.property.pojo.Vehicle;
import station.property.service.PropertyService;
import station.users.pojo.Users;

@Controller
@RequestMapping("/property")
public class PropertyController {
	@Autowired
	private PropertyService propertyService;
	
	
	
	@RequestMapping("/arrangementshow")
	public String groupAllshowonarrangement(Model model) {
		List<GroupApply> listp = propertyService.groupAllshowonarrangement();
		model.addAttribute("listp", listp);
		return "/property/arrangement";
	}
	
	@RequestMapping("/processingarrangement/{id}")
	public String processingarrangement(Model model,@PathVariable Integer id) {
		Integer group_id=id;
		List<Users> listu=this.propertyService.findOnChecker();
		List<Vehicle> listv=this.propertyService.findNullState();
		model.addAttribute("listu", listu);
		model.addAttribute("listv",listv);
		model.addAttribute("group_id", group_id);
		
		return "/property/arrangementresult";
		
	}
	
	@RequestMapping("/onarrangement")
	@ResponseBody
	public String onarrangement(HttpServletRequest request) {
		Integer group_id=Integer.valueOf(request.getParameter("group_id"));
		Integer users_id=Integer.valueOf(request.getParameter("userid"));
		Integer vehicle_id=Integer.valueOf(request.getParameter("vehicle"));
		Integer checker_id=Integer.valueOf(request.getParameter("checker"));
		
		this.propertyService.processingarrangement(group_id,users_id,vehicle_id,checker_id);
		return "1";
		
	}
	
	@RequestMapping("/showcheck")
	public String showcheck(Model model) {
		Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer user_id=user.getUserid();
		
		List<Arrangement> lista=this.propertyService.showcheck(user_id);
		model.addAttribute("lista", lista);
		return "/property/check";
		
	}
	
	@RequestMapping("/processingcheck/{id}")
	public String processingcheck(Model model,@PathVariable Integer id) {
		Integer arrangement_id=id;
		
		model.addAttribute("arrangement_id",arrangement_id);
		
		return "/property/checkresult";
		
	}
	
	@RequestMapping("/oncheck")
	@ResponseBody
	public String oncheck(HttpServletRequest request) {
		String result =request.getParameter("result");
		Integer arrangement_id=Integer.valueOf(request.getParameter("arrangement_id"));
		
		this.propertyService.savecheck(result,arrangement_id);
		return "1";
		
	}
	
	@RequestMapping("/repair")
	public String repair(Model model ) {
		List<Vehicle> listv=this.propertyService.findAll();
		model.addAttribute("listv", listv);
		
		return "/property/addrepair";
	}
	
	
	@RequestMapping("/saverepair")
	@ResponseBody
	public String saverepair(HttpServletRequest request) {
		Integer vehicle_id=Integer.valueOf(request.getParameter("vehicle_id"));
		String repair_result =request.getParameter("repair_result");
		BigDecimal repair_cost=new BigDecimal(request.getParameter("repair_cost"));
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		this.propertyService.saverepair(vehicle_id,repair_result,repair_cost,userid);
		return "1";
		
	}
	
	@RequestMapping("/vehicle")
	public String showvehicle(Model model) {
		List<Vehicle> listv=this.propertyService.findAll();
		model.addAttribute("listv", listv);
		
		return "/property/showvehicle";
	}
}
