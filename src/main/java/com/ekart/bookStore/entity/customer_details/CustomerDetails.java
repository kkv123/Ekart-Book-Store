package com.ekart.bookStore.entity.customer_details;

import com.ekart.bookStore.dto.CustomerDTO;
import com.ekart.bookStore.entity.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customer_details_table")
public class CustomerDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customer_id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String phone;

    public CustomerDetails(CustomerDTO customerDTO) {
        this.user = customerDTO.getUser();
        this.email = customerDTO.getEmail();
        this.name = customerDTO.getName();
        this.address = customerDTO.getAddress();
        this.phone = customerDTO.getPhone();
    }

}
