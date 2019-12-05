package station.check.pojo;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="t_passengerinspection")
public class PassengerInspection {
	

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="passengerinspection_id")
	private Integer passengerinspection_id;
	
	@Column(name="check_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date check_date;
	
	@Column(name="result")
	private String result;
	
	
	public Integer getPassengerinspection_id() {
		return passengerinspection_id;
	}

	public void setPassengerinspection_id(Integer passengerinspection_id) {
		this.passengerinspection_id = passengerinspection_id;
	}

	public Date getCheck_date() {
		return check_date;
	}

	public void setCheck_date(Date check_date) {
		this.check_date = check_date;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
