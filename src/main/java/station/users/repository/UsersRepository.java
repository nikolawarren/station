package station.users.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import station.users.pojo.Roles;
import station.users.pojo.Users;

public interface UsersRepository extends JpaRepository<Users, Integer>{
	@Query("from Users where useraccount=?")
	Users findByUseraAcount(String useraccount);
	
	@Query("from Users where roles=?")
	List<Users> findOnChecker(Roles roles);
	
}
