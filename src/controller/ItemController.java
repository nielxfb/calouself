package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import util.SessionManager;

import java.util.ArrayList;

public class ItemController {

    /**
     * Uploads an item with the specified details.
     *
     * @param name the name of the item
     * @param size the size of the item
     * @param category the category of the item
     * @param price the price of the item
     * @return a response containing the uploaded item or an error message
     */
    public static Response<Item> uploadItem(String name, String size, String category, String price) {
        Response<Boolean> response = checkItemValidation(name, category, size, price);
        if (!response.isSuccess) {
            return new ResponseBuilder<Item>(false).withMessage(response.message).build();
        }

        Item item = new Item("", name, category, size, Integer.parseInt(price), "Pending", SessionManager.getInstance().getUser().getUserId());
        item.save();
        return new ResponseBuilder<Item>(true).withMessage("Successfully uploaded item!").build();
    }

    /**
     * Retrieves all items.
     *
     * @return a response containing a list of all items or an error message
     */
    public static Response<ArrayList<Item>> getAll() {
        ArrayList<Item> items = Item.getAll();
        if (items.isEmpty()) {
            return new ResponseBuilder<ArrayList<Item>>(false).withMessage("There is no item yet.").build();
        }
        return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
    }

    /**
     * Retrieves all approved items.
     *
     * @return a response containing a list of approved items or an error message
     */
    public static Response<ArrayList<Item>> getApproved() {
        ArrayList<Item> items = Item.getApproved();
        if (items.isEmpty()) {
            return new ResponseBuilder<ArrayList<Item>>(false).withMessage("There is no item yet.").build();
        }
        return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
    }

    /**
     * Retrieves all pending items.
     *
     * @return a response containing a list of pending items or an error message
     */
    public static Response<ArrayList<Item>> getPending() {
        ArrayList<Item> items = Item.getPending();
        if (items.isEmpty()) {
            return new ResponseBuilder<ArrayList<Item>>(false).withMessage("There is no item yet.").build();
        }
        return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
    }

    /**
     * Deletes the specified item.
     *
     * @param item the item to delete
     * @return a response indicating the success or failure of the deletion
     */
    public static Response<Item> deleteItem(Item item) {
        if (item == null) {
            return new ResponseBuilder<Item>(false).withMessage("Please select an item!").build();
        }

        item.delete();
        return new ResponseBuilder<Item>(true).withMessage("Successfully deleted item!").build();
    }

    /**
     * Edits the specified item with the new details.
     *
     * @param id the ID of the item
     * @param name the new name of the item
     * @param size the new size of the item
     * @param category the new category of the item
     * @param price the new price of the item
     * @param status the new status of the item
     * @return a response containing the edited item or an error message
     */
    public static Response<Item> editItem(String id, String name, String size, String category, String price, String status) {
        Response<Boolean> response = checkItemValidation(name, category, size, price);
        if (!response.isSuccess) {
            return new ResponseBuilder<Item>(false).withMessage(response.message).build();
        }

        if (id.trim().isEmpty()) {
            return new ResponseBuilder<Item>(false).withMessage("Please select an item!").build();
        }

        Item item = new Item(id, name, category, size, Integer.parseInt(price), status, SessionManager.getInstance().getUser().getUserId());
        item.update();
        return new ResponseBuilder<Item>(true).withMessage("Successfully edited item!").build();
    }

    /**
     * Validates the item details.
     *
     * @param name the name of the item
     * @param category the category of the item
     * @param size the size of the item
     * @param price the price of the item
     * @return a response indicating the success or failure of the validation
     */
    public static Response<Boolean> checkItemValidation(String name, String category, String size, String price) {
        String error = "";
        if (name.trim().isEmpty() || category.trim().isEmpty() || size.trim().isEmpty() || price.trim().isEmpty()) {
            error = "Please fill all required fields!";
        } else if (name.length() < 3) {
            error = "Name must be at least 3 characters!";
        } else if (category.length() < 3) {
            error = "Category must be at least 3 characters!";
        }

        try {
            if (Integer.parseInt(price) == 0) {
                error = "Price must be greater than 0!";
            }
        } catch (Exception e) {
            error = "Price must be a number!";
        }

        if (!error.isEmpty()) {
            return new ResponseBuilder<Boolean>(false).withMessage(error).build();
        }

        return new ResponseBuilder<Boolean>(true).build();
    }

    /**
     * Approves the specified item.
     *
     * @param item the item to approve
     * @return a response indicating the success or failure of the approval
     */
    public static Response<Boolean> approveItem(Item item) {
        if (item == null) {
            return new ResponseBuilder<Boolean>(false).withMessage("Please select an item to decline!").build();
        }

        item.approve();
        return new ResponseBuilder<Boolean>(true).withMessage("Successfully approved item!").build();
    }

    /**
     * Declines the specified item with a reason.
     *
     * @param item the item to decline
     * @param reason the reason for declining the item
     * @return a response indicating the success or failure of the decline
     */
    public static Response<Boolean> declineItem(Item item, String reason) {
        if (item == null) {
            return new ResponseBuilder<Boolean>(false).withMessage("Please select an item to decline!").build();
        }

        if (reason.trim().isEmpty()) {
            return new ResponseBuilder<Boolean>(false).withMessage("Reason must not be empty!").build();
        }

        item.delete();
        return new ResponseBuilder<Boolean>(true).withMessage("Item is removed.").build();
    }

}