package com.cydeo.repository;

import com.cydeo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    // get User based on username
    User findByUserName(String username);

//    @Query("select u from User u where u.isDeleted=false ")
//    List<User> retrieveAllUsers();

    @Transactional
    void deleteByUserName(String username);


    List<User> findByRoleDescriptionIgnoreCase(String description);

}
