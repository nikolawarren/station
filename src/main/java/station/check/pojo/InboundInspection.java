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
@Table(name="t_inboundinspection")
public class InboundInspection {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="inboundinspection_id")
	private Integer inboundinspection_id;
	
	@Column(name="check_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date check_date;
	
	@Column(name="result")
	private String result;

	public Integer getInboundinspection_id() {
		return inboundinspection_id;
	}

	public void setInboundinspection_id(Integer inboundinspection_id) {
		this.inboundinspection_id = inboundinspection_id;
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
