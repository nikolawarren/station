package station.personal.pojo;

import java.math.BigDecimal;
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

import station.users.pojo.Users;


@Entity
@Table(name="t_ticket")
public class Ticket {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="ticket_id")
	private Integer ticket_id;
	
	
	@Column(name="ticket_price")
	private BigDecimal ticket_price;
	@Column(name="ticket_time")
	private String ticket_time;
	@Column(name="ticket_car_number")
	private String ticket_car_number;
	@Column(name="ticket_departure")
	private String ticket_departure;
	@Column(name="ticket_destination")
	private String ticket_destination;
	public Double getTicket_if_effect() {
		return ticket_if_effect;
	}
	
	@Column(name="ticket_date")
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date ticket_date;
	@Column(name="ticket_if_effect")
	private Double ticket_if_effect;
	public Date getTicket_date() {
		return ticket_date;
	}
	public void setTicket_date(Date ticket_date) {
		this.ticket_date = ticket_date;
	}
	@ManyToOne
	@JoinColumn(name="users_id")
	@JsonIgnore
	private Users users ;
	public Integer getTicket_id() {
		return ticket_id;
	}
	public void setTicket_id(Integer ticket_id) {
		this.ticket_id = ticket_id;
	}
	public BigDecimal getTicket_price() {
		return ticket_price;
	}
	public void setTicket_price(BigDecimal ticket_price) {
		this.ticket_price = ticket_price;
	}
	
	public String getTicket_time() {
		return ticket_time;
	}
	public void setTicket_time(String ticket_time) {
		this.ticket_time = ticket_time;
	}
	public void setTicket_if_effect(Double ticket_if_effect) {
		this.ticket_if_effect = ticket_if_effect;
	}
	public String getTicket_car_number() {
		return ticket_car_number;
	}
	public void setTicket_car_number(String ticket_car_number) {
		this.ticket_car_number = ticket_car_number;
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
	
	
	public Users getUsers() {
		return users;
	}
	public void setUsers(Users users) {
		this.users = users;
	}
	
}