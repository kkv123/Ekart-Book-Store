package com.ekart.bookStore.service.wishlist;

import com.ekart.bookStore.entity.product.Product;
import com.ekart.bookStore.entity.wishlist.WishList;
import com.ekart.bookStore.entity.wishlist.WishListItem;
import com.ekart.bookStore.repository.product.ProductRepo;
import com.ekart.bookStore.repository.wishlist.WishListItemRepo;
import com.ekart.bookStore.repository.wishlist.WishListRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class WishListServiceImpl implements WishListService {

    WishListRepo wishListRepo;
    WishListItemRepo wishListItemRepo;
    ProductRepo productRepo;

    @Autowired
    public WishListServiceImpl(WishListRepo wishListRepo, WishListItemRepo wishListItemRepo, ProductRepo productRepo) {
        this.wishListRepo = wishListRepo;
        this.wishListItemRepo = wishListItemRepo;
        this.productRepo = productRepo;
    }

    @Override
    public WishList getWishList(int userId) {
        return wishListRepo.findByUserId(userId);
    }

    @Override
    public void addtoWishList(int userId, int product_id) {
        WishList wishList = wishListRepo.findByUserId(userId);

        if (wishList == null) {
            wishList = new WishList();
            wishList.setUserId(userId);
            wishList.setCreatedAt(LocalDateTime.now());
            wishListRepo.save(wishList);
        }

        Product product = productRepo.findById(product_id).get();

        WishListItem wishListItem = new WishListItem();

        wishListItem.setProduct(product);
        wishListItem.setWishlist(wishList);

        wishList.getWishListItems().add(wishListItem);
        wishListItemRepo.save(wishListItem);

    }

    @Override
    public void removefromWishList(int wishListId) {
        Optional<WishListItem> optionalWishListItem = wishListItemRepo.findById(wishListId);

        if(optionalWishListItem.isPresent()) {
            WishListItem wishListItem = optionalWishListItem.get();

            WishList wishList=wishListItem.getWishlist();
            wishList.getWishListItems().remove(wishListItem);
            wishListItemRepo.delete(wishListItem);
        }

    }

}


