package sg.edu.nus.ophone.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.stereotype.Repository;
import sg.edu.nus.ophone.model.Users;

import java.util.List;

//code by Team3.Cynthia Peh
@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {

  @Query("SELECT u FROM Users u WHERE u.id = :id")
  List<Users> findByUserId(@Param("id") int id);

  @Query("SELECT u FROM Users u WHERE u.email = :email")
  List<Users> findByUserEmail(@Param("email") String email);

  @Query("SELECT u FROM Users u WHERE u.userType =:userType")
  List<Users> findByUserType(String userType);

  List<Users> findByName(String name);


}
