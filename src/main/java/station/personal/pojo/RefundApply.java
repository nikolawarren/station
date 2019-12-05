package station.personal.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import station.users.pojo.Users;

@Entity
@Table(name="t_refundapply")
public class RefundApply {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="refundapply_id")	
	private Integer refundapply_id;
	
	@Column(name="refundapply_applydate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date refundapply_applydate;
	
	@Column(name="refundapply_checkdate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date refundapply_checkdate;
	
	@ManyToOne
	@JoinColumn(name="apply_user_id")
	private Users apply_user;
	
	@ManyToOne
	@JoinColumn(name="check_user_id")
	private Users check_user;
	
	@Column(name="results")
	private String results;
	
	@Column(name="information")
	private String information;
	
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public Integer getRefundapply_id() {
		return refundapply_id;
	}

	public void setRefundapply_id(Integer refundapply_id) {
		this.refundapply_id = refundapply_id;
	}

	public Date getRefundapply_applydate() {
		return refundapply_applydate;
	}

	public void setRefundapply_applydate(Date refundapply_applydate) {
		this.refundapply_applydate = refundapply_applydate;
	}

	public Date getRefundapply_checkdate() {
		return refundapply_checkdate;
	}

	public void setRefundapply_checkdate(Date refundapply_checkdate) {
		this.refundapply_checkdate = refundapply_checkdate;
	}

	public Users getApply_user() {
		return apply_user;
	}

	public void setApply_user(Users apply_user) {
		this.apply_user = apply_user;
	}

	public Users getCheck_user() {
		return check_user;
	}

	public void setCheck_user(Users check_user) {
		this.check_user = check_user;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}
	
	
}
