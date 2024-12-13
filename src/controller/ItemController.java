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

}
