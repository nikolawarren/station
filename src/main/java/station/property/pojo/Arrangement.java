package station.property.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import station.group.pojo.GroupApply;
import station.users.pojo.Users;

@Entity
@Table(name="t_arrangement")
public class Arrangement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="arrangement_id")
	private Integer arrangement_id;
	
	@ManyToOne
	@JoinColumn(name="checkusers_id")
	private Users check_user;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	

	@ManyToOne
	@JoinColumn(name="arrangementuser_id")
	private Users arrangement_user;
	
	/*
	@OneToOne(mappedBy="arrangement")
	private GroupApply groupapply;
	*/
	@Column(name="arrangement_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date arrangement_date;
	
	@OneToOne
	@JoinColumn(name="check_id")
	private Check check;
	
	public Check getCheck() {
		return check;
	}

	public void setCheck(Check check) {
		this.check = check;
	}

	public Integer getArrangement_id() {
		return arrangement_id;
	}

	public void setArrangement_id(Integer arrangement_id) {
		this.arrangement_id = arrangement_id;
	}

	public Users getCheck_user() {
		return check_user;
	}

	public void setCheck_user(Users check_user) {
		this.check_user = check_user;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public Users getArrangement_user() {
		return arrangement_user;
	}

	public void setArrangement_user(Users arrangement_user) {
		this.arrangement_user = arrangement_user;
	}
	/*
	public GroupApply getGroupapply() {
		return groupapply;
	}

	public void setGroupapply(GroupApply groupapply) {
		this.groupapply = groupapply;
	}
	*/

	public Date getArrangement_date() {
		return arrangement_date;
	}

	public void setArrangement_date(Date arrangement_date) {
		this.arrangement_date = arrangement_date;
	}
}
