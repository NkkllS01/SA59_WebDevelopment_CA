package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sg.edu.nus.ophone.model.User;

import java.util.List;

//code by Team3.Cynthia Peh
public interface UserRepository extends JpaRepository<User, Integer> {

  @Query("SELECT u FROM Users u WHERE u.id = :id")
  List<User> findByUserId(@Param("id") int id);

  @Query("SELECT u FROM Users u WHERE u.email = :email")
  List<User> findByUserEmail(@Param("email") String email);

  @Query("SELECT u FROM Users u WHERE u.userType =:userType")
  List<User> findByUserType(String userType);
}
