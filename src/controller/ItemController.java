package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;

import java.util.ArrayList;

public class ItemController {

    public static Response<Item> uploadItem(String name, String size, String category, Integer price) {
        if (name.trim().isEmpty() || size.trim().isEmpty() || category.trim().isEmpty() || price == null) {
            return new ResponseBuilder<Item>(false).withMessage("All fields are required!").build();
        }

        Item item = new Item("", name, size, category, price, "Pending");
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

}
