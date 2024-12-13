package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;

import java.util.ArrayList;

public class ItemController {

    public static Response<ArrayList<Item>> getAll() {
        ArrayList<Item> items = Item.getAll();
        if (items.isEmpty()) {
            return new ResponseBuilder<ArrayList<Item>>(false).withMessage("There is no item yet.").build();
        }
        return new ResponseBuilder<ArrayList<Item>>(true).withData(items).build();
    }

    public static Response<Item> deleteItem(String itemId) {
        if (itemId.trim().isEmpty()) {
            return new ResponseBuilder<Item>(false).withMessage("Item ID is required!").build();
        }

        Item item = Item.find(itemId);
        if (item == null) {
            return new ResponseBuilder<Item>(false).withMessage("Item not found").build();
        }

        item.delete();
        return new ResponseBuilder<Item>(true).withMessage("Successfully deleted item!").build();
    }

}
