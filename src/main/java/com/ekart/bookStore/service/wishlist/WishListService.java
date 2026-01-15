package com.ekart.bookStore.service.wishlist;

import com.ekart.bookStore.entity.wishlist.WishList;

public interface WishListService {

    public void removefromWishList(int wishListId);
    public void addtoWishList(int userId,int product_id);
    public WishList getWishList(int userId);

}
