package model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

import util.Connect;

public class Wishlist {
	private String wishlistId;
	private String userId;
    private String itemId;
    
	public Wishlist(String wishlistId, String userId, String itemId) {
		this.wishlistId = !wishlistId.isEmpty() ? wishlistId : UUID.randomUUID().toString();
		this.userId = userId;
		this.itemId = itemId;
	}
	public String getWishlistId() {
		return wishlistId;
	}
	public void setWishlistId(String wishlistId) {
		this.wishlistId = wishlistId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
    
	public void add() {
        String query = "INSERT INTO wishlists (wishlist_Id, user_Id, item_Id) VALUES ('" + wishlistId + "', '" + userId + "', '" + itemId + "')";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }
	
	public static ArrayList<Item> getWishlistItems(String userId) {
	    String query = "SELECT w.wishlist_id, i.item_id, i.item_name, i.item_category, i.item_size, i.item_price " +
	                   "FROM wishlists w " +
	                   "JOIN items i ON w.item_id = i.item_id " +
	                   "WHERE w.user_id =  '"+ userId + "'";
	    ArrayList<Item> wishlistItems = new ArrayList<>();
	    Connect db = Connect.getConnection();
	    try {
	        
	        ResultSet rs = db.executeQuery(query);
	        while (rs.next()) {
	            Item item = new Item(
	                rs.getString("item_id"),
	                rs.getString("item_name"),
	                rs.getString("item_category"),
	                rs.getString("item_size"),
	                rs.getInt("item_price"),
	                null 
	            );
	            wishlistItems.add(item);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return null;
	    }
	    return wishlistItems;
	}

	
	


}
