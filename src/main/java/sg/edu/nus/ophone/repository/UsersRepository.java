package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.User;

import java.util.List;

//code by Team3.Cynthia Peh
@Repository
public interface UsersRepository extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM User u WHERE u.id = :id")
  List<User> findByUserId(@Param("id") int id);

  @Query("SELECT u FROM User u WHERE u.email = :email")
  List<User> findByUserEmail(@Param("email") String email);

  @Query("SELECT u FROM User u WHERE u.userType =:userType")
  List<User> findByUserType(String userType);

  List<User> findByName(String name);


}
