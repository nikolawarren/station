package station.group.pojo;

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

import com.fasterxml.jackson.annotation.JsonIgnore;

import station.personal.pojo.Ticket;
import station.users.pojo.Users;

@Entity
@Table(name="t_grouprefund")
public class GroupRefund {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="grouprefund_id")	
	private Integer grouprefund_id;
	
	@Column(name="grouprefund_applydate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date grouprefund_applydate;
	
	@Column(name="grouprefund_checkdate")	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date grouprefund_checkdate;
	
	@ManyToOne
	@JoinColumn(name="apply_user_id")
	@JsonIgnore
	private Users apply_user;
	
	@ManyToOne
	@JoinColumn(name="check_user_id")
	@JsonIgnore
	private Users check_user;
	
	@Column(name="results")
	private String results;
	
	@Column(name="information")
	private String information;
	
	public Integer getGrouprefund_id() {
		return grouprefund_id;
	}

	public void setGrouprefund_id(Integer grouprefund_id) {
		this.grouprefund_id = grouprefund_id;
	}

	public Date getGrouprefund_applydate() {
		return grouprefund_applydate;
	}

	public void setGrouprefund_applydate(Date grouprefund_applydate) {
		this.grouprefund_applydate = grouprefund_applydate;
	}

	public Date getGrouprefund_checkdate() {
		return grouprefund_checkdate;
	}

	public void setGrouprefund_checkdate(Date grouprefund_checkdate) {
		this.grouprefund_checkdate = grouprefund_checkdate;
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

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}

	public GroupApply getGroupapply() {
		return groupapply;
	}

	public void setGroupapply(GroupApply groupapply) {
		this.groupapply = groupapply;
	}

	@ManyToOne
	@JoinColumn(name="group_id")
	private GroupApply groupapply;
	
}
