package station.group.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import station.group.pojo.GroupApply;
import station.group.service.GroupService;
import station.personal.pojo.Ticket;
import station.users.pojo.Users;

@Controller
@RequestMapping("/group")
public class GroupController {

	@Autowired
	private GroupService groupService;
	
	@RequestMapping("/{page}")
	public String page(@PathVariable String page) {
		return "/group/"+page;
	}
	
	@RequestMapping("/savegroup")
	@ResponseBody
	public String savegroup(HttpServletRequest request){
		Integer person_number =Integer.valueOf(request.getParameter("person_number"));
		String group_date=request.getParameter("group_date");
		String group_time=request.getParameter("group_time");
		String group_departure=request.getParameter("group_departure");
		String group_destination=request.getParameter("group_destination");
		Integer user_id=Integer.valueOf(request.getParameter("userid"));
		
		this.groupService.savegroup(person_number, group_date, group_time, group_departure, group_destination, user_id);
		return "1";
		
	}
	
	@RequestMapping("/refundshow")
	@ResponseBody
	public List<GroupApply> refundshowlist(HttpServletRequest request) throws Exception{
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		List<GroupApply> list_g =groupService.refundshow(userid);
		
		return list_g;
		
	}
	
	@RequestMapping("/{page}/{id}")
	public String refund(Model model,@PathVariable String page,@PathVariable Integer id) {
		Integer group_id=id;
		model.addAttribute("group_id", group_id);
		return "/group/"+page;
		
	}
	
	
	@RequestMapping("/saverefund")
	@ResponseBody
	public String saverefund(HttpServletRequest request) throws Exception{
		String information=request.getParameter("information");
 		Integer apply_user_id=Integer.valueOf(request.getParameter("userid"));
		Integer group_id=Integer.valueOf(request.getParameter("group_id"));
		
		
		this.groupService.saverefund(group_id, information, apply_user_id);
		
		return "1";
	}
	
	@RequestMapping("/showgroup")
	public String showgroup(Model model) {
		Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer user_id=user.getUserid();
		List<GroupApply> listg=this.groupService.refundshow(user_id);
		model.addAttribute("listg", listg);
		return "/group/group";
		
	}
}
