package com.godigit.team2.repository.user;

import com.godigit.team2.entity.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    @Query("select u from User u where u.username = :userName")
    User findByUsername(String userName);

    @Query("select u from User u where u.user_id = :s")
    User findByUser_id(String s);
}
