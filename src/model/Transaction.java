package model;

import abstraction.Response;
import util.Connect;

import java.sql.ResultSet;
import java.util.ArrayList;
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

    public static ArrayList<Transaction> viewHistory(String userId) {
        String query = "SELECT * FROM transactions WHERE user_id = '" + userId + "'";
        ArrayList<Transaction> transactions = new ArrayList<>();
        Connect db = Connect.getConnection();
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                String transactionId = rs.getString("transaction_id");
                String itemId = rs.getString("item_id");
                transactions.add(new Transaction(transactionId, userId, itemId));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return transactions;
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
