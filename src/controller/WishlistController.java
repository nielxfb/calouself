package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import model.User;
import model.Wishlist;
import util.SessionManager;

import java.util.ArrayList;

public class WishlistController {

    /**
     * Checks if an item exists in the user's wishlist.
     *
     * @param userId the ID of the user
     * @param itemId the ID of the item
     * @return true if the item exists in the wishlist, false otherwise
     */
    public static Boolean itemExists(String userId, String itemId) {
        ArrayList<Wishlist> wishlists = Wishlist.getByUser(userId);
        if (wishlists == null || wishlists.isEmpty()) return false;
        for (Wishlist wishlist : wishlists) {
            if (wishlist.getItem().getItemId().equals(itemId)) return true;
        }
        return false;
    }

    /**
     * Adds an item to the user's wishlist.
     *
     * @param item the item to add to the wishlist
     * @return a response indicating the success or failure of the operation
     */
    public static Response<Wishlist> addToWishlist(Item item) {
        if (item == null) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Please select an item!").build();
        }

        User user = SessionManager.getInstance().getUser();
        if (itemExists(user.getUserId(), item.getItemId())) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Item already in wishlist!").build();
        }

        Wishlist wishlist = new Wishlist("", user.getUserId(), item.getItemId(), item);
        wishlist.save();
        return new ResponseBuilder<Wishlist>(true).withMessage("Successfully added to wishlist!").build();
    }

    /**
     * Retrieves the user's wishlist.
     *
     * @return a response containing the wishlist or an error message
     */
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

    /**
     * Removes an item from the user's wishlist.
     *
     * @param item the item to remove from the wishlist
     * @return a response indicating the success or failure of the operation
     */
    public static Response<Wishlist> removeWishlist(Item item) {
        if (item == null) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Please select an item first!").build();
        }

        User user = SessionManager.getInstance().getUser();
        if (!itemExists(user.getUserId(), item.getItemId())) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Item not found in wishlist!").build();
        }

        Wishlist wishlist = Wishlist.getByItemAndUser(item.getItemId(), user.getUserId());
        if (wishlist == null) {
            return new ResponseBuilder<Wishlist>(false).withMessage("Wishlist not found!").build();
        }

        wishlist.remove();
        return new ResponseBuilder<Wishlist>(true).withMessage("Successfully removed from wishlist!").build();
    }

}