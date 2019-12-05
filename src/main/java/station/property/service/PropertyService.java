package station.property.service;

import java.math.BigDecimal;
import java.util.List;

import station.group.pojo.GroupApply;
import station.property.pojo.Arrangement;
import station.property.pojo.Vehicle;
import station.users.pojo.Users;

public interface PropertyService {
	List<GroupApply> groupAllshowonarrangement();
	
	List<Users> findOnChecker();
	
	List<Vehicle> findNullState();
	
	List<Vehicle> findAll();
	
	void processingarrangement(Integer group_id,Integer users_id,Integer vehicle_id,Integer checker_id);
	
	List<Arrangement> showcheck(Integer user_id);
	
	void savecheck(String result,Integer arrangement_id);
	
	void saverepair(Integer vehicle_id,String repair_result,BigDecimal repair_cost,Integer userid);
	
}
