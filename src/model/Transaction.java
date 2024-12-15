package model;

import util.Connect;

import java.util.UUID;

public class Transaction {

    private String transactionId;
    private String userId;
    private String itemId;

    public Transaction(String transactionId, String userId, String itemId) {
        this.transactionId = transactionId.isEmpty() ? UUID.randomUUID().toString() : transactionId;
        this.userId = userId;
        this.itemId = itemId;
    }

    public void save() {
        String query = "INSERT INTO transactions (transaction_id, user_id, item_id) VALUES ('" + transactionId + "', '" + userId + "', '" + itemId + "')";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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
}
