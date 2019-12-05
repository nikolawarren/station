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
@Table(name="t_inboundarrangement")
public class InboundArrangement {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inboundarrangement_id")
	private Integer inboundarrangement_id;
	
	@ManyToOne
	@JoinColumn(name="check_user_id")
	private Users check_user;
	
	@Column(name="arrangement_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date arrangement_date;
	
	@Column(name="entrance")
	private String entrance;
	
	@ManyToOne
	@JoinColumn(name="arrangement_user_id")
	private Users arrangement_user;
	
	@OneToOne
	@JoinColumn(name="inboundinspection_id")
	InboundInspection inboundinspection;
	
	public Integer getInboundarrangement_id() {
		return inboundarrangement_id;
	}

	public void setInboundarrangement_id(Integer inboundarrangement_id) {
		this.inboundarrangement_id = inboundarrangement_id;
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

	public String getEntrance() {
		return entrance;
	}

	public void setEntrance(String entrance) {
		this.entrance = entrance;
	}

	public Users getArrangement_user() {
		return arrangement_user;
	}

	public void setArrangement_user(Users arrangement_user) {
		this.arrangement_user = arrangement_user;
	}

	public InboundInspection getInboundinspection() {
		return inboundinspection;
	}

	public void setInboundinspection(InboundInspection inboundinspection) {
		this.inboundinspection = inboundinspection;
	}

	
}
