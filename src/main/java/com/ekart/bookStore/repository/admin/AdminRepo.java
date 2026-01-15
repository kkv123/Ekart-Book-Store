package com.ekart.bookStore.repository.admin;

import com.ekart.bookStore.entity.admin.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepo extends JpaRepository<Admin, Integer> {
    @Query("select a from Admin a where a.username = ?1")
    Admin findByUsername(String username);

    @Query("select a from Admin a where a.id = ?1")
    Admin findByUser_id(String userId);
}
