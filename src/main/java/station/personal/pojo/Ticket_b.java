package station.personal.pojo;

public class Ticket_b {
	private String ticket_time;
	private String trip;
	public String getTicket_time() {
		return ticket_time;
	}
	public void setTicket_time(String ticket_time) {
		this.ticket_time = ticket_time;
	}
	public String getTrip() {
		return trip;
	}
	public Ticket_b(String ticket_time, String trip) {
		super();
		this.ticket_time = ticket_time;
		this.trip = trip;
	}
	public Ticket_b() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setTrip(String trip) {
		this.trip = trip;
	}
}
