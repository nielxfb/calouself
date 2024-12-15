package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import model.User;
import model.Wishlist;
import util.SessionManager;

import java.util.ArrayList;

public class WishlistController {

    public static Boolean itemExists(String userId, String itemId) {
        ArrayList<Wishlist> wishlists = Wishlist.getByUser(userId);
        if (wishlists == null || wishlists.isEmpty()) return false;
        for (Wishlist wishlist : wishlists) {
            if (wishlist.getItem().getItemId().equals(itemId)) return true;
        }
        return false;
    }

    public static Response<Wishlist> addToWishlist(String itemId) {
        Item item = Item.find(itemId);
        if (item == null) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Item not found!").build();
        }

        User user = SessionManager.getInstance().getUser();
        if (itemExists(user.getUserId(), itemId)) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Item already in wishlist!").build();
        }

        Wishlist wishlist = new Wishlist("", user.getUserId(), itemId, item);
        wishlist.save();
        return new ResponseBuilder<Wishlist>(true).withMessage("Successfully added to wishlist!").build();
    }

    public static Response<ArrayList<Wishlist>> getWishlist() {
        User user = SessionManager.getInstance().getUser();
        if (user == null) {
            return new ResponseBuilder<ArrayList<Wishlist>>(false).withMessage("User not found!").build();
        }

        ArrayList<Wishlist> wishlists = Wishlist.getByUser(user.getUserId());
        if (wishlists == null || wishlists.isEmpty()) {
            return new ResponseBuilder<ArrayList<Wishlist>>(false).withMessage("Wishlist is empty.").build();
        }

        return new ResponseBuilder<ArrayList<Wishlist>>(true).withData(wishlists).build();
    }

}
