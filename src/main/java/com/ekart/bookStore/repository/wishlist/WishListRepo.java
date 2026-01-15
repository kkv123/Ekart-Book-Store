package com.ekart.bookStore.repository.wishlist;

import com.ekart.bookStore.entity.wishlist.WishList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListRepo extends JpaRepository<WishList, Integer> {
    WishList findByUserId(int userId);
}
