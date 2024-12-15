package controller;

import java.util.ArrayList;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import model.User;
import model.Wishlist;
import util.Connect;

public class WishlistController {

	public WishlistController() {
		// TODO Auto-generated constructor stub
	}
	
	public static Response<ArrayList<Item>> viewWishlist(String userId) {
	    ArrayList<Item> items = Wishlist.getWishlistItems(userId);
	    if (items.isEmpty()) {
	        return new ResponseBuilder<ArrayList<Item>>(false).withMessage("Your wishlist is empty.").build();
	    }
	    return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
	}

	
	public static Response<Wishlist> addWishlist(String itemId, String userId){
		Wishlist wishlist = new Wishlist("", userId, itemId);
		wishlist.add();

		return new ResponseBuilder<Wishlist>(true).withData(wishlist).build();
	}
	
	
}
