package station.property.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="t_vehicle")
public class Vehicle {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="vehicle_id")
	private Integer vehicle_id;
	
	
	@Column(name="vehicle_number")
	private String vehicle_number;
	
	@Column(name="repair_times")
	private Integer repair_times;
	
	public Integer getRepair_times() {
		return repair_times;
	}

	public void setRepair_times(Integer repair_times) {
		this.repair_times = repair_times;
	}

	@Column(name="state")
	private String state;
	
	
	

	public Integer getVehicle_id() {
		return vehicle_id;
	}

	public void setVehicle_id(Integer vehicle_id) {
		this.vehicle_id = vehicle_id;
	}

	public String getVehicle_number() {
		return vehicle_number;
	}

	public void setVehicle_number(String vehicle_number) {
		this.vehicle_number = vehicle_number;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
}
