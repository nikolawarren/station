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
@Table(name="t_changeapply")
public class ChangeApply {@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="changeapply_id")	
	private Integer changeapply_id;

	@Column(name="information")	
	private String information;
	
	@Column(name="ticket_time")	
	private String ticket_time;
	
	@Column(name="ticket_departure")	
	private String ticket_departure;
	
	@Column(name="ticket_destination")
	private String ticket_destination;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="changeapply_applytime")
	private Date changeapply_applytime;
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	@Column(name="changeapply_checktime")
	private Date changeapply_checktime;
	
	@ManyToOne
	@JoinColumn(name="apply_user_id")
	private Users apply_user;
	
	@ManyToOne
	@JoinColumn(name="check_user_id")
	private Users check_user;
	
	@ManyToOne
	@JoinColumn(name="ticket_id")
	private Ticket ticket;
	public String getInformation() {
		return information;
	}
	
	public Integer getChangeapply_id() {
		return changeapply_id;
	}

	public void setChangeapply_id(Integer changeapply_id) {
		this.changeapply_id = changeapply_id;
	}

	public String getTicket_time() {
		return ticket_time;
	}

	public void setTicket_time(String ticket_time) {
		this.ticket_time = ticket_time;
	}

	public String getTicket_departure() {
		return ticket_departure;
	}

	public void setTicket_departure(String ticket_departure) {
		this.ticket_departure = ticket_departure;
	}

	public String getTicket_destination() {
		return ticket_destination;
	}

	public void setTicket_destination(String ticket_destination) {
		this.ticket_destination = ticket_destination;
	}

	public Date getChangeapply_applytime() {
		return changeapply_applytime;
	}

	public void setChangeapply_applytime(Date changeapply_applytime) {
		this.changeapply_applytime = changeapply_applytime;
	}

	public Date getChangeapply_checktime() {
		return changeapply_checktime;
	}

	public void setChangeapply_checktime(Date changeapply_checktime) {
		this.changeapply_checktime = changeapply_checktime;
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

	public Ticket getTicket() {
		return ticket;
	}

	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	@Column(name="results")
	private String results;
}
