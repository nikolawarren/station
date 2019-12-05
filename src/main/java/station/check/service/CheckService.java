package station.check.service;

import java.util.List;

import station.check.pojo.InboundArrangement;
import station.check.pojo.PassengerArrangement;
import station.users.pojo.Users;

public interface CheckService {
	List<Users> showcheckeroninbound();
	
	void saveinboundarrangement(String entrance,Integer checkusers_id,Integer arrangemntusers_id);
	
	List<InboundArrangement> Oninboundbychecker(Integer checkusers_id);
	
	void saveinboundinpspection (String result,Integer inboundarrangement_id);
	
	List<InboundArrangement> showInbound();
	
	List<Users> showcheckeronpassenger();
	
	void savepassengerarrangement(String entrance,Integer checkusers_id,Integer arrangemntusers_id);
	
	List<PassengerArrangement> Onpassengerbychecker(Integer checkusers_id);
	
	void savepassengerinpspection (String result,Integer passengerarrangement_id);
	
	List<PassengerArrangement> showPassenger();
}
