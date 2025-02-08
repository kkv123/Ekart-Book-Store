package com.godigit.team2.repository.feedback;

import com.godigit.team2.entity.feedback.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Integer> {
    @Query("Select f from Feedback f where f.productId= :productId")
    List<Feedback> findByProductId(int productId);

    @Query("Select f from Feedback f where f.userId= :userId")
    List<Feedback> allFeedbackByUserId(String userId);
}
