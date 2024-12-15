package model;

import util.Connect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

public class Offer {

    private String offerId;
    private String userId;
    private User user;
    private String username;
    private String itemId;
    private Item item;
    private String itemName;
    private Integer itemPrice;
    private Integer offerPrice;

    public Offer(String offerId, String userId, String itemId, Integer offerPrice) {
        this.offerId = offerId.isEmpty() ? UUID.randomUUID().toString() : offerId;
        this.userId = userId;
        this.itemId = itemId;
        this.offerPrice = offerPrice;
    }

    public void save() {
        String query = "INSERT INTO offers (offer_id, user_id, item_id, offer_price) VALUES ('" + offerId + "', '" + userId + "', '" + itemId + "', " + offerPrice + ")";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public static Integer getHighestOffer(String itemId) {
        String query = "SELECT MAX(offer_price) FROM offers WHERE item_id = '" + itemId + "'";
        Connect db = Connect.getConnection();
        try {
            ResultSet rs = db.executeQuery(query);
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return null;
    }

    public static ArrayList<Offer> getBySellerId(String sellerId) {
        String query = "SELECT * FROM offers WHERE item_id IN (SELECT item_id FROM items WHERE seller_id = '" + sellerId + "')";
        ArrayList<Offer> offers = new ArrayList<>();
        Connect db = Connect.getConnection();
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                String userId = rs.getString("user_id");
                String itemId = rs.getString("item_id");
                String offerId = rs.getString("offer_id");
                Integer offerPrice = rs.getInt("offer_price");
                offers.add(new Offer(offerId, userId, itemId, offerPrice));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return offers;
    }

    public void remove() {
        String query = "DELETE FROM offers WHERE offer_id = '" + offerId + "'";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public String getOfferId() {
        return offerId;
    }

    public void setOfferId(String offerId) {
        this.offerId = offerId;
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

    public Integer getOfferPrice() {
        return offerPrice;
    }

    public void setOfferPrice(Integer offerPrice) {
        this.offerPrice = offerPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getUsername() {
        return user.getUsername();
    }

    public void setUsername(String username) {
        this.user.setUsername(username);
    }

    public String getItemName() {
        return item.getItemName();
    }

    public void setItemName(String itemName) {
        item.setItemName(itemName);
    }

    public Integer getItemPrice() {
        return item.getItemPrice();
    }

    public void setItemPrice(Integer itemPrice) {
        item.setItemPrice(itemPrice);
    }
}
