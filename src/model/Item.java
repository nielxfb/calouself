package model;

import util.Connect;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.UUID;

public class Item {

    private String itemId;
    private String itemName;
    private String itemCategory;
    private String itemSize;
    private Integer itemPrice;
    private String itemStatus;
    private String sellerId;

    public Item(String itemId, String itemName, String itemCategory, String itemSize, Integer itemPrice, String itemStatus, String sellerId) {
        this.itemId = !itemId.isEmpty() ? itemId : UUID.randomUUID().toString();
        this.itemName = itemName;
        this.itemCategory = itemCategory;
        this.itemSize = itemSize;
        this.itemPrice = itemPrice;
        this.itemStatus = itemStatus;
        this.sellerId = sellerId;
    }

    public static ArrayList<Item> getApproved() {
        String query = "SELECT * FROM items WHERE item_status = 'Approved'";
        ArrayList<Item> items = new ArrayList<>();
        Connect db = Connect.getConnection();
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                Item item = new Item(rs.getString("item_id"), rs.getString("item_name"), rs.getString("item_category"), rs.getString("item_size"), rs.getInt("item_price"), rs.getString("item_status"), rs.getString("seller_id"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return items;
    }

    public static ArrayList<Item> getPending() {
        String query = "SELECT * FROM items WHERE item_status = 'Pending'";
        ArrayList<Item> items = new ArrayList<>();
        Connect db = Connect.getConnection();
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                Item item = new Item(rs.getString("item_id"), rs.getString("item_name"), rs.getString("item_category"), rs.getString("item_size"), rs.getInt("item_price"), rs.getString("item_status"), rs.getString("seller_id"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return items;
    }

    public static ArrayList<Item> getAll() {
        String query = "SELECT * FROM items";
        ArrayList<Item> items = new ArrayList<>();
        Connect db = Connect.getConnection();
        try {
            ResultSet rs = db.executeQuery(query);
            while (rs.next()) {
                Item item = new Item(rs.getString("item_id"), rs.getString("item_name"), rs.getString("item_category"), rs.getString("item_size"), rs.getInt("item_price"), rs.getString("item_status"), rs.getString("seller_id"));
                items.add(item);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return items;
    }

    public static Item find(String itemId) {
        String query = "SELECT * FROM items WHERE item_id = '" + itemId + "'";
        Connect db = Connect.getConnection();
        try (ResultSet rs = db.executeQuery(query)) {
            if (rs.next()) {
                return new Item(rs.getString("item_id"), rs.getString("item_name"), rs.getString("item_category"), rs.getString("item_size"), rs.getInt("item_price"), rs.getString("item_status"), rs.getString("seller_id"));
            }
        } catch (Exception e) {
            return null;
        }
        return null;
    }

    public void update() {
        String query = "UPDATE items SET item_name = '" + itemName + "', item_category = '" + itemCategory + "', item_size = '" + itemSize + "', item_price = " + itemPrice + ", item_status = '" + itemStatus + "' WHERE item_id = '" + itemId + "'";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public void delete() {
        String query = "DELETE FROM items WHERE item_id = '" + itemId + "'";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public void save() {
        String query = "INSERT INTO items (item_id, item_name, item_category, item_size, item_price, item_status, seller_id) VALUES ('" + itemId + "', '" + itemName + "', '" + itemCategory + "', '" + itemSize + "', " + itemPrice + ", '" + itemStatus + "', '" + sellerId + "')";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public void approve() {
        String query = "UPDATE items set item_status = 'Approved' WHERE item_id = '" + itemId + "'";
        Connect db = Connect.getConnection();
        db.executeUpdate(query);
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCategory() {
        return itemCategory;
    }

    public void setItemCategory(String itemCategory) {
        this.itemCategory = itemCategory;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public Integer getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemStatus() {
        return itemStatus;
    }

    public void setItemStatus(String itemStatus) {
        this.itemStatus = itemStatus;
    }

    public String getSellerId() {
        return sellerId;
    }

    public void setSellerId(String sellerId) {
        this.sellerId = sellerId;
    }
}
