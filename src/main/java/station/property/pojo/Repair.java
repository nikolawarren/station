package station.property.pojo;

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

import station.users.pojo.Users;

@Entity
@Table(name="t_repair")
public class Repair {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="repair_id")
	private Integer repair_id;
	
	@OneToOne
	@JoinColumn(name="vehicle_id")
	private Vehicle vehicle;
	
	@Column(name="repair_cost")
	private BigDecimal repair_cost;
	
	@Column(name="repair_result")
	private String repair_result;
	
	@Column(name="repair_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date repair_date;
	
	@ManyToOne
	@JoinColumn(name="approvaluser_id")
	private Users approvaluser;
	
	public Users getApprovaluser() {
		return approvaluser;
	}

	public void setApprovaluser(Users approvaluser) {
		this.approvaluser = approvaluser;
	}

	@ManyToOne
	@JoinColumn(name="checkuser_id")
	private Users checkuser;
	
	@Column(name="result")
	private String result;
	
	@Column(name="check_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date check_date;

	public Integer getRepair_id() {
		return repair_id;
	}

	public void setRepair_id(Integer repair_id) {
		this.repair_id = repair_id;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}

	public BigDecimal getRepair_cost() {
		return repair_cost;
	}

	public void setRepair_cost(BigDecimal repair_cost) {
		this.repair_cost = repair_cost;
	}

	public String getRepair_result() {
		return repair_result;
	}

	public void setRepair_result(String repair_result) {
		this.repair_result = repair_result;
	}

	public Date getRepair_date() {
		return repair_date;
	}

	public void setRepair_date(Date repair_date) {
		this.repair_date = repair_date;
	}

	public Users getCheckuser() {
		return checkuser;
	}

	public void setCheckuser(Users checkuser) {
		this.checkuser = checkuser;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}
	
}
