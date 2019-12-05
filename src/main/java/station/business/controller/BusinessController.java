package station.business.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import station.business.service.BusinessService;
import station.group.pojo.GroupApply;
import station.group.pojo.GroupRefund;
import station.group.service.GroupService;
import station.personal.pojo.ChangeApply;
import station.personal.pojo.RefundApply;
import station.personal.service.PersonalService;
import station.property.pojo.Repair;

@Controller
@RequestMapping("/business")
public class BusinessController {
	@Autowired
	private PersonalService personalService;
	@Autowired
	private GroupService groupService;
	@Autowired
	private BusinessService businessService;
	
	
	@RequestMapping("/refundshow")
	public String refundshow(Model model) {
		List<RefundApply> listp=this.personalService.refundlist();
		List<GroupRefund> listg=this.groupService.refundlist();
		
		model.addAttribute("listp", listp);
		model.addAttribute("listg", listg);
		
		return "/business/refund";
		
	}
	
	@RequestMapping("/changeshow")
	public String changeshow(Model model) {
		List<ChangeApply> listp=this.personalService.changelist();
		model.addAttribute("listp", listp);

		return "/business/change";
		
	}
	
	@RequestMapping("/groupshowoncheck")
	public String groupshowoncheck(Model model) {
		List<GroupApply> listp=this.businessService.groupAllshowoncheck();
		model.addAttribute("listp", listp);
		return "/business/groupcheck";
		
	}
	
	@RequestMapping("/groupshowonapproval")
	public String groupshowonapproval(Model model) {
		List<GroupApply> listp=this.businessService.groupAllshowonapproval();
		model.addAttribute("listp", listp);
		return "/business/groupapproval";
		
	}
	
	@RequestMapping("/processingrefund/{id}")
	public String processingrefund(Model model,@PathVariable Integer id,HttpServletRequest request) {
 		Integer refund_id =id;
		String type=request .getParameter("type");
		model.addAttribute("refund_id", refund_id);
		model.addAttribute("type", type);
		return "/business/refundresult";
	}
	
	@RequestMapping("/processingchange/{id}")
	public String processingchange(Model model,@PathVariable Integer id,HttpServletRequest request) {
 		Integer change_id =id;		
		model.addAttribute("change_id", change_id);
		return "/business/changeresult";
	}
	
	@RequestMapping("/editrefund")
	@ResponseBody
	public String editrefund(HttpServletRequest request) {
		String type=request.getParameter("type");
		Integer refund_id=Integer.valueOf(request.getParameter("refund_id"));
		String result=request.getParameter("result");
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		if(type.equals("团体订票")) {
			this.businessService.processingrefundonGroup(refund_id, result, userid);
		}
		else if(type.equals("个体订票")) {
			this.businessService.processingrefundonPersonal(refund_id, result,userid);
		}
		
		return "1";
	}
	
	@RequestMapping("/editchange")
	@ResponseBody
	public String editchange(HttpServletRequest request) {
	
		Integer change_id=Integer.valueOf(request.getParameter("change_id"));
		String result=request.getParameter("result");
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		this.businessService.processingchange(change_id, result, userid);
		
		return "1";
	}
	
	@RequestMapping("/check/{id}")
	public String check(Model model,@PathVariable Integer id) {
		Integer group_id=id;
		model.addAttribute("group_id", group_id);
		return "/business/checkresult";
		
	}
	
	@RequestMapping("/approval/{id}")
	public String approval(Model model,@PathVariable Integer id) {
		Integer group_id=id;
		model.addAttribute("group_id", group_id);
		return "/business/approvalresult";
		
	}
	
	@RequestMapping("/oncheck")
	@ResponseBody
	public String check(HttpServletRequest request) {
		
		String result=request.getParameter("result");
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		Integer group_id=Integer.valueOf(request.getParameter("group_id"));
		
		
		this.businessService.check(group_id, result, userid);
		
		return "1";
	}
	
	@RequestMapping("/onapproval")
	@ResponseBody
	public String approval(HttpServletRequest request) {
		
		String result=request.getParameter("result");
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		Integer group_id=Integer.valueOf(request.getParameter("group_id"));
		
		
		this.businessService.approval(group_id, result, userid);
		
		return "1";
	}
	
	@RequestMapping("/repair")
	public String showrepair(Model model) {
		List<Repair> listr=this.businessService.showrepair();
		model.addAttribute("listr", listr);
		return "/business/showrepair";
	}
	
	@RequestMapping("/processingrepair/{id}")
	public String processingrepair(Model model,@PathVariable String id) {
		Integer repair_id=Integer.valueOf(id);
		model.addAttribute("repair_id", repair_id);
		return "/business/repairresult";
	}
	
	
	@RequestMapping("/onrepair")
	@ResponseBody
	public String repair(HttpServletRequest request) {
		
		String result=request.getParameter("result");
		Integer userid=Integer.valueOf(request.getParameter("userid"));
		Integer repair_id=Integer.valueOf(request.getParameter("repair_id"));
		
		
		this.businessService.repair(repair_id, result, userid);
		
		return "1";
	}
}
