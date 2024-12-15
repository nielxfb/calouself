package model;

import util.Connect;

import java.sql.ResultSet;
import java.util.UUID;

public class Offer {

    private String offerId;
    private String userId;
    private String itemId;
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
}
