package station.personal.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import station.personal.pojo.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Integer>,JpaSpecificationExecutor<Ticket>{
	
	@Query("from Ticket where users_id=? and ticket_if_effect= 1")
	List<Ticket> showeffect(Integer user_id);
	
	
}
