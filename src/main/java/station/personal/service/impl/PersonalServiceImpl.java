package station.personal.service.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import station.personal.pojo.ChangeApply;
import station.personal.pojo.RefundApply;
import station.personal.pojo.Ticket;
import station.personal.repository.ChangeApplyRepository;
import station.personal.repository.RefundApplyRepository;
import station.personal.repository.TicketRepository;
import station.personal.service.PersonalService;
import station.users.pojo.Users;
import station.users.repository.UsersRepository;

@Service
public class PersonalServiceImpl implements PersonalService{
	@Autowired
	private TicketRepository TicketRepository;
	@Autowired
	private UsersRepository usersRepository;	
	@Autowired
	private RefundApplyRepository refundApplyRepository;
	
	@Autowired
	private ChangeApplyRepository changeApplyRepository;
	
	@Override
	public List<Ticket> showeffect(Integer user_id) {
		return TicketRepository.showeffect(user_id);
	}

	@Override
	public Ticket save(String ticket_date, String ticket_time, String ticket_departure, String ticket_destination,
			Integer user_id) {
		Ticket ticket=new Ticket();
		 
		try {
			DateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
			Date date=format.parse(ticket_date);
			
			ticket.setTicket_date(date);
			ticket.setTicket_time(ticket_time);
			ticket.setTicket_departure(ticket_departure);
			ticket.setTicket_destination(ticket_destination);
			ticket.setTicket_if_effect(Double.valueOf(1));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Users users=this.usersRepository.findOne(user_id);
		ticket.setUsers(users);

		return this.TicketRepository.save(ticket);
	}

	@Override
	public void saveRefundApply(String information, Integer userid, Integer ticket_id) {
		RefundApply refundApply=new RefundApply();
		refundApply.setInformation(information);
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			refundApply.setRefundapply_applydate(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Users users=this.usersRepository.findOne(userid);
		Ticket ticket=this.TicketRepository.findOne(ticket_id);
		refundApply.setApply_user(users);
		refundApply.setTicket(ticket);
		
		this.refundApplyRepository.save(refundApply);
		
	}

	@Override
	public void saveChangeApply(String information, String ticket_time, String ticket_departure,
			String ticket_destination, Integer userid, Integer ticket_id) {
		ChangeApply changeapply=new ChangeApply();
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			changeapply.setChangeapply_applytime(date);
			changeapply.setTicket_time(ticket_time);
			changeapply.setTicket_departure(ticket_departure);
			changeapply.setTicket_destination(ticket_destination);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Users users=this.usersRepository.findOne(userid);
		Ticket ticket=this.TicketRepository.findOne(ticket_id);
		
		changeapply.setApply_user(users);
		changeapply.setTicket(ticket);
		changeapply.setInformation(information);
		this.changeApplyRepository.save(changeapply);
	}

	@Override
	public List<RefundApply> refundlist() {
		
		return this.refundApplyRepository.findRefundAll();
	}

	@Override
	public List<ChangeApply> changelist() {
		
		return this.changeApplyRepository.findChangeAll();
	}

	@Override
	public List<Ticket> conditionalquery(String ticket_departure, String ticket_destination) {
		Specification<Ticket> spec=new Specification<Ticket>() {

			@Override
			public Predicate toPredicate(Root<Ticket> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> list=new ArrayList<Predicate>();
				if(ticket_departure!=null&&!ticket_departure.isEmpty())
					list.add(cb.equal(root.get("ticket_departure"), ticket_departure));
				if(ticket_destination!=null&&!ticket_destination.isEmpty())
					list.add(cb.equal(root.get("ticket_destination"), ticket_destination));
				Predicate[] arr =new Predicate[list.size()];
				
				return cb.and(list.toArray(arr));
			}
		};
		return this.TicketRepository.findAll(spec);
	}

	

}
