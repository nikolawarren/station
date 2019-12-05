package station.group.pojo;

import java.math.BigDecimal;
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

import com.fasterxml.jackson.annotation.JsonIgnore;

import station.property.pojo.Arrangement;
import station.property.pojo.Vehicle;
import station.users.pojo.Users;

@Entity
@Table(name="t_group")
public class GroupApply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="group_id")
	private Integer group_id;
	
	

	@Column(name="person_number")
	private Integer person_number;
	
	@ManyToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@Column(name="group_departurer")
	private String group_departure;
	
	@Column(name="group_destination")
	private String group_destination;
	
	@Column(name="group_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date group_date;
	
	@Column(name="group_time")
	private String group_time;
	
	@Column(name="group_price")
	private BigDecimal group_price;
	
	@Column(name="group_applydate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date group_applydate;
	
	@ManyToOne
	@JoinColumn(name="applyusers_id")
	@JsonIgnore
	private Users apply_user;
	
	@Column(name="group_checkdate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date group_checkdate;
	
	@ManyToOne
	@JoinColumn(name="checkusers_id")
	private Users check_user;
	
	@Column(name="check_result")	
	private String check_result;
	
	@Column(name="group_approvaldate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date group_approvaldate;
	
	@ManyToOne
	@JoinColumn(name="approvalusers_id")
	private Users approval_user;
	
	@Column(name="approval_result")
	private String approval_result;
	
	@OneToOne
	@JoinColumn(name="arrangement_id")
	private Arrangement arrangement;
	
	
	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public Integer getPerson_number() {
		return person_number;
	}

	public void setPerson_number(Integer person_number) {
		this.person_number = person_number;
	}

	
	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public String getGroup_departure() {
		return group_departure;
	}

	public void setGroup_departure(String group_departure) {
		this.group_departure = group_departure;
	}

	public String getGroup_destination() {
		return group_destination;
	}

	public void setGroup_destination(String group_destination) {
		this.group_destination = group_destination;
	}

	public Date getGroup_date() {
		return group_date;
	}

	public void setGroup_date(Date group_date) {
		this.group_date = group_date;
	}

	public String getGroup_time() {
		return group_time;
	}

	public void setGroup_time(String group_time) {
		this.group_time = group_time;
	}

	public BigDecimal getGroup_price() {
		return group_price;
	}

	public void setGroup_price(BigDecimal group_price) {
		this.group_price = group_price;
	}

	public Date getGroup_applydate() {
		return group_applydate;
	}

	public void setGroup_applydate(Date group_applydate) {
		this.group_applydate = group_applydate;
	}

	public Users getApply_user() {
		return apply_user;
	}

	public void setApply_user(Users apply_user) {
		this.apply_user = apply_user;
	}

	public Date getGroup_checkdate() {
		return group_checkdate;
	}

	public void setGroup_checkdate(Date group_checkdate) {
		this.group_checkdate = group_checkdate;
	}

	public Users getCheck_user() {
		return check_user;
	}

	public void setCheck_user(Users check_user) {
		this.check_user = check_user;
	}

	public String getCheck_result() {
		return check_result;
	}

	public void setCheck_result(String check_result) {
		this.check_result = check_result;
	}

	public Date getGroup_approvaldate() {
		return group_approvaldate;
	}

	

	public void setGroup_approvaldate(Date group_approvaldate) {
		this.group_approvaldate = group_approvaldate;
	}

	public Users getApproval_user() {
		return approval_user;
	}

	public void setApproval_user(Users approval_user) {
		this.approval_user = approval_user;
	}

	public String getApproval_result() {
		return approval_result;
	}

	public void setApproval_result(String approval_result) {
		this.approval_result = approval_result;
	}
	public Arrangement getArrangement() {
		return arrangement;
	}

	public void setArrangement(Arrangement arrangement) {
		this.arrangement = arrangement;
	}
}
