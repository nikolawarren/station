package station.property.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="t_check")
public class Check {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="check_id")
	private Integer check_id;
	
	@Column(name="result")
	private String result;
	
	@Column(name="check_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date check_date;
	
	

	public Integer getCheck_id() {
		return check_id;
	}

	public void setCheck_id(Integer check_id) {
		this.check_id = check_id;
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
