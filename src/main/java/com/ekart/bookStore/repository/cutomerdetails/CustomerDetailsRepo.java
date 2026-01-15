package com.ekart.bookStore.repository.cutomerdetails;

import com.ekart.bookStore.entity.customer_details.CustomerDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerDetailsRepo extends JpaRepository<CustomerDetails, Integer> {
}
