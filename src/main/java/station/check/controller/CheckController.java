package station.check.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import station.check.pojo.InboundArrangement;
import station.check.pojo.PassengerArrangement;
import station.check.service.CheckService;
import station.users.pojo.Users;

@Controller
@RequestMapping("/check")
public class CheckController {
	@Autowired
	private CheckService checkService;
	
	@RequestMapping("/showinbound")
	public String showincheck(Model model) {
		List<String> liste=new ArrayList<String>();
		liste.add("北口");
		liste.add("东口");
		liste.add("南口");
		liste.add("西口");
		model.addAttribute("liste", liste);
		List<Users> listu=this.checkService.showcheckeroninbound();
		model.addAttribute("listi", listu);
		return "/check/addinarrangment";
		
	}
	
	@RequestMapping("/showpassenger")
	public String showpacheck(Model model) {
		List<String> liste=new ArrayList<String>();
		liste.add("1号检票口");
		liste.add("2号检票口");
		liste.add("3号检票口");
		liste.add("4号检票口");
		liste.add("5号检票口");
		liste.add("6号检票口");
		liste.add("7号检票口");
		liste.add("8号检票口");
		model.addAttribute("liste", liste);
		List<Users> listu=this.checkService.showcheckeronpassenger();
		model.addAttribute("listi", listu);
		return "/check/addpaarrangment";
		
	}
	
	
	
	@RequestMapping("/saveinboundarrangement")
	@ResponseBody
	public String inboundarrangement(HttpServletRequest request) {
		String entrance=request.getParameter("entrance");
		Integer checkusers_id=Integer.valueOf(request.getParameter("checkusers_id"));
		Integer arrangemntusers_id=Integer.valueOf(request.getParameter("userid"));
		
		this.checkService.saveinboundarrangement(entrance,checkusers_id,arrangemntusers_id);
		return "1";
		
	}
	
	@RequestMapping("/savepassengerarrangement")
	@ResponseBody
	public String passengerarrangement(HttpServletRequest request) {
		String entrance=request.getParameter("entrance");
		Integer checkusers_id=Integer.valueOf(request.getParameter("checkusers_id"));
		Integer arrangemntusers_id=Integer.valueOf(request.getParameter("userid"));

		this.checkService.savepassengerarrangement(entrance,checkusers_id,arrangemntusers_id);
		return "1";
		
	}
	
	@RequestMapping("/showinboundonchecker")
	public String showinboundonchecker(Model model) {
		Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer checkusers_id=user.getUserid();
		List<InboundArrangement> listi=this.checkService.Oninboundbychecker(checkusers_id);
		model.addAttribute("listi", listi);
		return "/check/inboundonchecker";
		
	}
	
	@RequestMapping("/showpassengeronchecker")
	public String showpassengeronchecker(Model model) {
		Users user=(Users)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Integer checkusers_id=user.getUserid();
		List<PassengerArrangement> listi=this.checkService.Onpassengerbychecker(checkusers_id);
		model.addAttribute("listi", listi);
		return "/check/passengeronchecker";
		
	}
	
	
	@RequestMapping("/processingIncheck/{id}")
	public String processingIncheck(Model model,@PathVariable String id) {
		Integer inboundarrangement_id=Integer.valueOf(id);
		model.addAttribute("inboundarrangement_id", inboundarrangement_id);
		return "/check/inboundcheckresult";
		
	}
	
	@RequestMapping("/processingPacheck/{id}")
	public String processingPacheck(Model model,@PathVariable String id) {
		Integer passengerarrangement_id=Integer.valueOf(id);
		model.addAttribute("passengerarrangement_id", passengerarrangement_id);
		return "/check/passengercheckresult";
		
	}
	
	@RequestMapping("/saveincheck")
	@ResponseBody
	public String saveincheck(HttpServletRequest request) {
		String result =request.getParameter("result");
		Integer inboundarrangement_id=Integer.valueOf(request.getParameter("inboundarrangement_id"));
		
		this.checkService.saveinboundinpspection(result,inboundarrangement_id);
		return "1";
		
	}
	
	@RequestMapping("/savepacheck")
	@ResponseBody
	public String savepacheck(HttpServletRequest request) {
		String result =request.getParameter("result");
		Integer passengerarrangement_id=Integer.valueOf(request.getParameter("passengerarrangement_id"));
		
		this.checkService.savepassengerinpspection(result,passengerarrangement_id);
		return "1";
		
	}
	
	
	@RequestMapping("/showallInbound")
	public String showInbound(Model model) {
		List<InboundArrangement> listi=this.checkService.showInbound();
		model.addAttribute("listi", listi);
		return "/check/inbound";
	}
	
	@RequestMapping("/showallPassenger")
	public String showPassenger(Model model) {
		List<PassengerArrangement> listi=this.checkService.showPassenger();
		model.addAttribute("listi", listi);
		return "/check/passenger";
	}
		
}
