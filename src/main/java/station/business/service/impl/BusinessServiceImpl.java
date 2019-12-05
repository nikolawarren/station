package station.business.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.business.service.BusinessService;
import station.group.pojo.GroupApply;
import station.group.pojo.GroupRefund;
import station.group.repository.GroupRefundRepository;
import station.group.repository.GroupRepository;
import station.personal.pojo.ChangeApply;
import station.personal.pojo.RefundApply;
import station.personal.pojo.Ticket;
import station.personal.repository.ChangeApplyRepository;
import station.personal.repository.RefundApplyRepository;
import station.personal.repository.TicketRepository;
import station.property.pojo.Repair;
import station.property.respository.RepairRepository;
import station.users.pojo.Users;
import station.users.repository.UsersRepository;

@Service
public class BusinessServiceImpl implements BusinessService{
	@Autowired
	RefundApplyRepository refundApplyRepository;
	@Autowired
	ChangeApplyRepository changeApplyRepository;
	@Autowired
	GroupRefundRepository groupRefundRepository;
	@Autowired
	TicketRepository ticketRepository;
	@Autowired
	GroupRepository groupRepository;
	@Autowired 
	UsersRepository usersRepository;
	@Autowired
	RepairRepository repairRepository;
	

	

	@Override
	public void processingchange(Integer change_id,String result,Integer userid) {
		ChangeApply changeapply=this.changeApplyRepository.findOne(change_id);
		changeapply.setResults(result);
		Users users=this.usersRepository.findOne(userid);
		changeapply.setCheck_user(users);
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(format.format(new Date()));
			changeapply.setChangeapply_checktime(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.changeApplyRepository.save(changeapply);
		
		Ticket ticket =changeapply.getTicket();
		ticket.setTicket_time(changeapply.getTicket_time());
		ticket.setTicket_departure(changeapply.getTicket_departure());
		ticket.setTicket_destination(changeapply.getTicket_destination());
		
		this.ticketRepository.save(ticket);
		
	}

	@Override
	public void check(Integer group_id,String result,Integer userid) {
		GroupApply groupapply =this.groupRepository.findOne(group_id);
		groupapply.setCheck_result(result);
		
		
		Users users=this.usersRepository.findOne(userid);
		groupapply.setCheck_user(users);
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(format.format(new Date()));
			groupapply.setGroup_checkdate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.groupRepository.save(groupapply);
	}

	@Override
	public void approval(Integer group_id,String result,Integer userid) {
		GroupApply groupapply =this.groupRepository.findOne(group_id);
		groupapply.setApproval_result(result);
		
		Users users=this.usersRepository.findOne(userid);
		groupapply.setApproval_user(users);
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(format.format(new Date()));
			groupapply.setGroup_approvaldate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.groupRepository.save(groupapply);
	}

	@Override
	public void processingrefundonGroup(Integer refund_id,String result,Integer userid) {
		GroupRefund groupRefund =this.groupRefundRepository.findOne(refund_id);
		groupRefund.setResults(result);
		Users users =this.usersRepository.findOne(userid);
		groupRefund.setCheck_user(users);
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(format.format(new Date()));
			groupRefund.setGrouprefund_checkdate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GroupApply groupapply=this.groupRepository.findOne(groupRefund.getGroupapply().getGroup_id());
		groupapply.setApply_user(null);
		this.groupRefundRepository.save(groupRefund);
		this.groupRepository.save(groupapply);
	}

	@Override
	public void processingrefundonPersonal(Integer refund_id,String result,Integer userid) {
		RefundApply refundApply =this.refundApplyRepository.findOne(refund_id);
		refundApply.setResults(result);
		Users users =this.usersRepository.findOne(userid);
		refundApply.setCheck_user(users);
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(format.format(new Date()));
			refundApply.setRefundapply_checkdate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.refundApplyRepository.save(refundApply);
		
	}

	@Override
	public List<GroupApply> groupAllshowoncheck() {
		
		return this.groupRepository.groupAllshowoncheck();
	}

	@Override
	public List<GroupApply> groupAllshowonapproval() {
		// TODO Auto-generated method stub
		return this.groupRepository.groupAllshowonapproval();
	}

	@Override
	public List<Repair> showrepair() {
		// TODO Auto-generated method stub
		return this.repairRepository.showrepairNoApproval();
	}

	@Override
	public void repair(Integer repair_id, String result, Integer userid) {
		Repair repair =this.repairRepository.findOne(repair_id);
		repair.setResult(result);	
		Users approvaluser=this.usersRepository.findOne(userid);
		repair.setApprovaluser(approvaluser);
		
		DateFormat format =  new SimpleDateFormat("yyyy-MM-dd");
		Date date;
		try {
			date = format.parse(format.format(new Date()));
			repair.setCheck_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.repairRepository.save(repair);
		
	}
	
}
