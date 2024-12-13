package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;

import java.util.ArrayList;

public class ItemController {

    public static Response<Item> uploadItem(String name, String size, String category, String price) {
        Response<Boolean> response = checkItemValidation(name, category, size, price);
        if (!response.isSuccess) {
            return new ResponseBuilder<Item>(false).withMessage(response.message).build();
        }

        Item item = new Item("", name, size, category, Integer.parseInt(price), "Pending");
        item.save();
        return new ResponseBuilder<Item>(true).withMessage("Successfully uploaded item!").build();
    }

    public static Response<ArrayList<Item>> getAll() {
        ArrayList<Item> items = Item.getAll();
        if (items.isEmpty()) {
            return new ResponseBuilder<ArrayList<Item>>(false).withMessage("There is no item yet.").build();
        }
        return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
    }

    public static Response<ArrayList<Item>> getApproved() {
        ArrayList<Item> items = Item.getApproved();
        if (items.isEmpty()) {
            return new ResponseBuilder<ArrayList<Item>>(false).withMessage("There is no item yet.").build();
        }
        return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
    }

    public static Response<Item> deleteItem(Item item) {
        if (item == null) {
            return new ResponseBuilder<Item>(false).withMessage("Please select an item!").build();
        }

        item.delete();
        return new ResponseBuilder<Item>(true).withMessage("Successfully deleted item!").build();
    }

    public static Response<Boolean> checkItemValidation(String name, String category, String size, String price) {
        String error = "";
        if (name.trim().isEmpty() || category.trim().isEmpty() || size.trim().isEmpty() || price.trim().isEmpty()) {
            error = "Please fill all required fields!";
        }

        try {
            Integer.parseInt(price);
        } catch (NumberFormatException e) {
            error = "Price must be a number!";
        }

        if (Integer.parseInt(price) == 0) {
            error = "Price must be greater than 0!";
        }

        if (name.length() < 3) {
            error = "Name must be at least 3 characters!";
        }

        if (category.length() < 3) {
            error = "Category must be at least 3 characters!";
        }

        if (!error.isEmpty()) {
            return new ResponseBuilder<Boolean>(false).withMessage(error).build();
        }

        return new ResponseBuilder<Boolean>(true).build();
    }

}
