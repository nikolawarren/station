package station.check.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.check.pojo.InboundArrangement;
import station.check.pojo.InboundInspection;
import station.check.pojo.PassengerArrangement;
import station.check.pojo.PassengerInspection;
import station.check.repository.InboundArrangementRepository;
import station.check.repository.InboundInspectionRepository;
import station.check.repository.PassengerArrangementRepository;
import station.check.repository.PassengerInspectionRepository;
import station.check.service.CheckService;
import station.users.pojo.Roles;
import station.users.pojo.Users;
import station.users.repository.RolesRepository;
import station.users.repository.UsersRepository;


@Service
public class CheckServiceImpl implements CheckService{
	
	@Autowired
	private UsersRepository usersRepository;
	@Autowired
	private RolesRepository rolesRepository;
	@Autowired
	private InboundArrangementRepository inboundArrangementRepository;
	@Autowired
	private InboundInspectionRepository inboundInspectionRepository;
	@Autowired
	private PassengerArrangementRepository passengerArrangementRepository;
	@Autowired
	private PassengerInspectionRepository passengerInspectionRepository;
	
	public List<Users> showcheckeroninbound() {
		Roles roles=rolesRepository.findOne(4);
		List<Users> list=this.usersRepository.findOnChecker(roles);
		return list ;
	}


	
	public void saveinboundarrangement(String entrance, Integer checkusers_id, Integer arrangemntusers_id) {
		InboundArrangement inboundarrangement =new InboundArrangement();
		inboundarrangement.setEntrance(entrance);
		
		Users checkusers=this.usersRepository.findOne(checkusers_id);
		inboundarrangement.setCheck_user(checkusers);
		
		Users arrangementusers =this.usersRepository.findOne(arrangemntusers_id);
		inboundarrangement.setArrangement_user(arrangementusers);
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			inboundarrangement.setArrangement_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.inboundArrangementRepository.save(inboundarrangement);
	}



	@Override
	public List<InboundArrangement> Oninboundbychecker(Integer checkusers_id) {
		Users users=this.usersRepository.findOne(checkusers_id);
		return this.inboundArrangementRepository.Oninboundbychecker(users);
	}


	public void saveinboundinpspection(String result, Integer inboundarrangement_id) {
		InboundInspection inboundinspection =new InboundInspection();
		inboundinspection.setResult(result);
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			inboundinspection.setCheck_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.inboundInspectionRepository.save(inboundinspection);
		
		InboundArrangement inboundArrangement =this.inboundArrangementRepository.findOne(inboundarrangement_id);
		inboundArrangement.setInboundinspection(inboundinspection);
		this.inboundArrangementRepository.save(inboundArrangement);
		
		
	}
	
	
	@Override
	public List<InboundArrangement> showInbound() {
		// TODO Auto-generated method stub
		return this.inboundArrangementRepository.findAll();
	}
	
	@Override
	public List<Users> showcheckeronpassenger() {
		Roles roles=rolesRepository.findOne(5);
		List<Users> list=this.usersRepository.findOnChecker(roles);
		return list ;
	}



	@Override
	public void savepassengerarrangement(String entrance, Integer checkusers_id, Integer arrangemntusers_id) {
		PassengerArrangement passengerarrangement =new PassengerArrangement();
		passengerarrangement.setEntrance(entrance);
		
		Users checkusers=this.usersRepository.findOne(checkusers_id);
		passengerarrangement.setCheck_user(checkusers);
		
		Users arrangementusers =this.usersRepository.findOne(arrangemntusers_id);
		passengerarrangement.setArrangement_user(arrangementusers);
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			passengerarrangement.setArrangement_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.passengerArrangementRepository.save(passengerarrangement);
		
	}



	@Override
	public List<PassengerArrangement> Onpassengerbychecker(Integer checkusers_id) {
		Users users=this.usersRepository.findOne(checkusers_id);
		return this.passengerArrangementRepository.OnPassengerbychecker(users);
		
	}


	



	@Override
	public void savepassengerinpspection(String result, Integer passengerarrangement_id) {
		PassengerInspection passengerinspection =new PassengerInspection();
		passengerinspection.setResult(result);
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			passengerinspection.setCheck_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.passengerInspectionRepository.save(passengerinspection);
		
		PassengerArrangement passengerarrangement =this.passengerArrangementRepository.findOne(passengerarrangement_id);
		passengerarrangement.setPassengerinspection(passengerinspection);
		this.passengerArrangementRepository.save(passengerarrangement);
		
	}





	@Override
	public List<PassengerArrangement> showPassenger() {
		// TODO Auto-generated method stub
		return this.passengerArrangementRepository.findAll();
	}

}
