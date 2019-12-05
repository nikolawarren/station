package station.check.pojo;

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

import station.users.pojo.Users;

@Entity
@Table(name="t_passengerarrangement")
public class PassengerArrangement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="passengerarrangement_id")
	private Integer passengerarrangement_id;
	
	
	@Column(name="entrance")
	private String entrance;

	@ManyToOne
	@JoinColumn(name="check_user_id")
	private Users check_user;
	
	@Column(name="arrangement_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date arrangement_date;
	
	
	@ManyToOne
	@JoinColumn(name="arrangement_user_id")
	private Users arrangement_user;
	
	
	@OneToOne
	@JoinColumn(name="passengerinspection_id")
	private PassengerInspection passengerinspection;

	public PassengerInspection getPassengerinspection() {
		return passengerinspection;
	}

	public void setPassengerinspection(PassengerInspection passengerinspection) {
		this.passengerinspection = passengerinspection;
	}

	public Integer getPassengerarrangement_id() {
		return passengerarrangement_id;
	}

	public void setPassengerarrangement_id(Integer passengerarrangement_id) {
		this.passengerarrangement_id = passengerarrangement_id;
	}

	public Users getCheck_user() {
		return check_user;
	}

	public void setCheck_user(Users check_user) {
		this.check_user = check_user;
	}

	public Date getArrangement_date() {
		return arrangement_date;
	}

	public void setArrangement_date(Date arrangement_date) {
		this.arrangement_date = arrangement_date;
	}

	
	public Users getArrangement_user() {
		return arrangement_user;
	}

	public void setArrangement_user(Users arrangement_user) {
		this.arrangement_user = arrangement_user;
	}
	
	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	
}
