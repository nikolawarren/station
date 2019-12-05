package station.property.service.impl;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import station.group.pojo.GroupApply;
import station.group.repository.GroupRepository;
import station.property.pojo.Arrangement;
import station.property.pojo.Check;
import station.property.pojo.Repair;
import station.property.pojo.Vehicle;
import station.property.respository.ArrangementRespository;
import station.property.respository.CheckRepository;
import station.property.respository.RepairRepository;
import station.property.respository.VehicleRepository;
import station.property.service.PropertyService;
import station.users.pojo.Roles;
import station.users.pojo.Users;
import station.users.repository.RolesRepository;
import station.users.repository.UsersRepository;

@Service
public class PropertyServiceImpl implements PropertyService{

	@Autowired
	GroupRepository groupRepository;
	@Autowired 
	UsersRepository usersRepository;
	@Autowired
	RolesRepository rolesRepository;
	@Autowired
	VehicleRepository vehicleRepository;
	@Autowired
	ArrangementRespository arrangementRepository;
	@Autowired
	CheckRepository checkrepository;
	@Autowired
	RepairRepository repairRepository;
	
	public List<GroupApply> groupAllshowonarrangement() {
		
		return this.groupRepository.groupAllshowonarrangement();
	}

	@Override
	public List<Users> findOnChecker() {
		Roles roles=rolesRepository.findOne(3);
		return usersRepository.findOnChecker(roles);
	}

	@Override
	public List<Vehicle> findNullState() {
		
		return this.vehicleRepository.showVehicleNoState();
	}

	@Override
	public void processingarrangement(Integer group_id, Integer users_id, Integer vehicle_id, Integer checker_id) {
		GroupApply groupApply =this.groupRepository.findOne(group_id);
		Users users =this.usersRepository.findOne(users_id);
		Users checker=this.usersRepository.findOne(checker_id);
		Vehicle vehicle=this.vehicleRepository.findOne(vehicle_id);
		Arrangement arrangement=new Arrangement();
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			arrangement.setArrangement_date(date);
			arrangement.setCheck_user(checker);
			arrangement.setVehicle(vehicle);
			arrangement.setArrangement_user(users);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		groupApply.setArrangement(arrangement);
		vehicle.setState("需检查");
		this.arrangementRepository.save(arrangement);
		this.groupRepository.save(groupApply);
	}

	@Override
	public List<Arrangement> showcheck(Integer user_id) {
		Users users =this.usersRepository.findOne(user_id);
		return this.arrangementRepository.Oncheckbychecker(users);
	}

	@Override
	public void savecheck(String result, Integer arrangement_id) {
		Arrangement arrangement =this.arrangementRepository.findOne(arrangement_id);
		Check check=new Check();
		
		GroupApply groupapply=this.groupRepository.selectbyArrangement(arrangement);
		
		Vehicle vehicle=arrangement.getVehicle();
		vehicle.setState("待使用");
		
		groupapply.setVehicle(vehicle);
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			check.setCheck_date(date);
			check.setResult(result);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.checkrepository.save(check);
		
		arrangement.setCheck(check);
		this.arrangementRepository.save(arrangement);
		
		this.groupRepository.save(groupapply);
	}

	@Override
	public List<Vehicle> findAll() {
		
		return this.vehicleRepository.findAll();
	}

	@Override
	public void saverepair(Integer vehicle_id, String repair_result, BigDecimal repair_cost, Integer userid) {
		Vehicle vehicle =this.vehicleRepository.findOne(vehicle_id);
		int count=vehicle.getRepair_times();
		count++;
		vehicle.setRepair_times(count);
		vehicle.setState("待审核");
		
		Repair repair=new Repair();
		repair.setRepair_cost(repair_cost);
		repair.setRepair_result(repair_result);
		repair.setVehicle(vehicle);
		Users checker=this.usersRepository.findOne(userid);
		repair.setCheckuser(checker);
		
		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd"); 
		String strdate=format.format(new Date());
		try {
			Date date=format.parse(strdate);
			repair.setRepair_date(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		this.vehicleRepository.save(vehicle);
		this.repairRepository.save(repair);
	}

}
