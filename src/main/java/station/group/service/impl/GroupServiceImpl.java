package station.group.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.group.pojo.GroupApply;
import station.group.pojo.GroupRefund;
import station.group.repository.GroupRefundRepository;
import station.group.repository.GroupRepository;
import station.group.service.GroupService;
import station.users.pojo.Users;
import station.users.repository.UsersRepository;


@Service
public class GroupServiceImpl implements GroupService{

	@Autowired
	private UsersRepository usersRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private GroupRefundRepository grouprefundRepository;
	
	@Override
	public void savegroup(Integer person_number, String group_date, String group_time, String group_departure,
			String group_destination, Integer user_id) {
		GroupApply groupapply=new GroupApply();
		groupapply.setPerson_number(person_number);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date date=format.parse(group_date);
			String strdate=format.format(new Date());
			
			Date now=format.parse(strdate);
			groupapply.setGroup_date(date);
			groupapply.setGroup_applydate(now);
			groupapply.setGroup_time(group_time);
			groupapply.setGroup_departure(group_departure);
			groupapply.setGroup_destination(group_destination);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			Users user= usersRepository.findOne(user_id);
			groupapply.setApply_user(user);
			
			this.groupRepository.save(groupapply);
		
	}

	@Override
	public List<GroupApply> refundshow(Integer user_id) {
		Users users =usersRepository.findOne(user_id);
		return groupRepository.refundshow(users);
	}

	@Override
	public void saverefund(Integer group_id, String information, Integer user_id) {
		GroupRefund grouprefund =new GroupRefund();
		grouprefund.setInformation(information);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		try {
			Date date=format.parse(format.format(new Date()));
			grouprefund.setGrouprefund_applydate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		GroupApply groupapply=this.groupRepository.findOne(group_id);
		grouprefund.setGroupapply(groupapply);
		
		Users users=this.usersRepository.findOne(user_id);
		grouprefund.setApply_user(users);
		
		this.grouprefundRepository.save(grouprefund);
	}

	@Override
	public List<GroupRefund> refundlist() {
		
		return this.grouprefundRepository.findRefundAll();
	}

}
