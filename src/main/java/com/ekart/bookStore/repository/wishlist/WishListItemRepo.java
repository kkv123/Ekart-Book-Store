package com.ekart.bookStore.repository.wishlist;

import com.ekart.bookStore.entity.wishlist.WishListItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WishListItemRepo extends JpaRepository<WishListItem, Integer> {
}
