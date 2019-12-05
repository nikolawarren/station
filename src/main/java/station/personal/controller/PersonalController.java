package station.personal.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import station.personal.pojo.Ticket;
import station.personal.pojo.Ticket_b;
import station.personal.service.PersonalService;

@Controller
@RequestMapping("/personal")
public class PersonalController {
	
	@Autowired
	private PersonalService personalService;
	
	@RequestMapping("/{page}")
	public String direct(@PathVariable String page) {
		return "/personal/"+page;
	}
	
	@RequestMapping("/create")
	@ResponseBody
	public String create(HttpServletRequest request) {
		String ticket_date=request.getParameter("ticket_date");
		String ticket_time=request.getParameter("ticket_time");
		String ticket_departure=request.getParameter("ticket_departure");
		String ticket_destination=request.getParameter("ticket_destination");
		Integer usersid=Integer.valueOf(request.getParameter("userid"));
		
		this.personalService.save(ticket_date,ticket_time,ticket_departure,ticket_destination,usersid);
		//这里需要赋值
		
		return "1";
	}
	
	@RequestMapping("/listshow")
	@ResponseBody
	public List<Ticket_b> list(HttpServletRequest request) throws Exception{
		String ticket_departure=request.getParameter("ticket_departure");
		String ticket_destination=request.getParameter("ticket_destination");
		String trip="从"+ticket_departure+"至"+ticket_destination;
		
		Ticket_b T1=new Ticket_b("08:00",trip);
		Ticket_b T2=new Ticket_b("10:00",trip);
		Ticket_b T3=new Ticket_b("12:00",trip);
		Ticket_b T4=new Ticket_b("13:00",trip);
		Ticket_b T5=new Ticket_b("15:00",trip);
		Ticket_b T6=new Ticket_b("16:00",trip);
		
		List<Ticket_b> list_tb=new ArrayList<Ticket_b>();
		list_tb.add(T1);
		list_tb.add(T2);
		list_tb.add(T3);
		list_tb.add(T4);
		list_tb.add(T5);
		list_tb.add(T6);
		return list_tb;
		
	}
	
	
	/*
		供退换票两个节点的展示的使用
	*/
	@RequestMapping("/refundshow")
	@ResponseBody
	public List<Ticket> refundshowlist(HttpServletRequest request) throws Exception{
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		List<Ticket> list_te =personalService.showeffect(userid);
		
		return list_te;
		
	}
	
	@RequestMapping("/{page}/{id}")
	public String refund(Model model,@PathVariable String page,@PathVariable Integer id) {
		Integer ticket_id=id;
		model.addAttribute("ticket_id", ticket_id);
		return "/personal/"+page;
		
	}
	
	@RequestMapping("/saverefund")
	@ResponseBody
	public String saverefund(HttpServletRequest request) {
		String information=request.getParameter("information");
		
		Integer apply_user_id=Integer.valueOf(request.getParameter("userid"));
		Integer ticket_id =Integer.valueOf(request.getParameter("ticket_id"));
		
		this.personalService.saveRefundApply(information, apply_user_id, ticket_id);
		return "1";

	}
	
	@RequestMapping("/savechange")
	@ResponseBody
	public String savechange(HttpServletRequest request) {
		String information=request.getParameter("information");
		String ticket_time=request.getParameter("ticket_time");
		String ticket_departure=request.getParameter("ticket_departure");
		String ticket_destination=request.getParameter("ticket_destination");
		Integer apply_user_id=Integer.valueOf(request.getParameter("userid"));
		Integer ticket_id =Integer.valueOf(request.getParameter("ticket_id"));
		
		this.personalService.saveChangeApply(information, ticket_time,ticket_departure,ticket_destination,apply_user_id, ticket_id);
		return "1";

	}
	
	@RequestMapping("/showdetail/{id}")
	public String ticketdetail(@PathVariable String id) {
		return "/personal/detail";
		
	}
	
	@RequestMapping("Query")
	@ResponseBody
	public List<Ticket> Query(HttpServletRequest request){
		String ticket_departure=request.getParameter("ticket_departure");
		String ticket_destination=request.getParameter("ticket_destination");
		List<Ticket> list=this.personalService.conditionalquery(ticket_departure,ticket_destination);
		return list;
	}
	
	
	
}
