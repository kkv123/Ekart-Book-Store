package com.godigit.team2.entity.feedback;

import com.godigit.team2.entity.product.Product;
import com.godigit.team2.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name ="feedback_details")
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int feedback_id;

    @JoinColumn(name="user_id")
    private String userId;

    @JoinColumn(name = "product_id")
    private int productId;

    @Column(nullable = false)
    private int rating;

    @Column(columnDefinition = "TEXT")
    private String comment;

    @Column(nullable = false,updatable = false)
    private LocalDateTime created_at=LocalDateTime.now();

}
