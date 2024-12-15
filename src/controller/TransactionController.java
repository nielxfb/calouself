package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import model.Transaction;
import model.User;
import model.Wishlist;
import util.SessionManager;

public class TransactionController {

    public static Response<Transaction> createTransaction(Item item) {
        if (item == null) {
            return new ResponseBuilder<Transaction>(false).withMessage("Please select an item first!").build();
        }

        User user = SessionManager.getInstance().getUser();
        Transaction transaction = new Transaction("", user.getUserId(), item.getItemId());
        transaction.save();
        Wishlist.removeItem(item.getItemId());

        return new ResponseBuilder<Transaction>(true).withMessage("Successfully purchased item!").build();
    }

}
