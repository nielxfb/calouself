package model;

import abstraction.Response;
import util.Connect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

public class Wishlist {

    private String wishlistId;
    private String userId;
    private String itemId;
    private Item item;

    public Wishlist(String wishlistId, String userId, String itemId, Item item) {
        this.wishlistId = wishlistId.isEmpty() ? UUID.randomUUID().toString() : wishlistId;
        this.userId = userId;
        this.itemId = itemId;
        this.item = item;
    }

    public void save() {
        String query = "INSERT INTO wishlists (wishlist_id, user_id, item_id) VALUES ('" + wishlistId + "', '" + userId + "', '" + itemId + "')";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public static Wishlist getByItemAndUser(String itemId, String userId) {
        String query = "SELECT * FROM wishlists WHERE item_id = '" + itemId + "' AND user_id = '" + userId + "'";
        Connect db = Connect.getConnection();
        Item item = Item.find(itemId);
        try {
            ResultSet rs = db.executeQuery(query);
            if (rs.next()) {
                return new Wishlist(rs.getString("wishlist_id"), rs.getString("user_id"), rs.getString("item_id"), item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static ArrayList<Wishlist> getByUser(String userId) {
        String query = "SELECT * FROM wishlists JOIN items ON wishlists.item_id = items.item_id WHERE user_id = '" + userId + "'";
        Connect db = Connect.getConnection();
        ArrayList<Wishlist> wishlists = new ArrayList<>();
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                Item item = new Item(rs.getString("item_id"), rs.getString("item_name"), rs.getString("item_category"), rs.getString("item_size"), rs.getInt("item_price"), rs.getString("item_status"), rs.getString("seller_id"));
                Wishlist wishlist = new Wishlist(rs.getString("wishlist_id"), rs.getString("user_id"), rs.getString("item_id"), item);
                wishlists.add(wishlist);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return wishlists;
    }

    public void remove() {
        String query = "DELETE FROM wishlists WHERE wishlist_id = '" + wishlistId + "'";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public static void removeItem(String itemId) {
        String query = "DELETE FROM wishlists WHERE item_id = '" + itemId + "'";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
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

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }
}
