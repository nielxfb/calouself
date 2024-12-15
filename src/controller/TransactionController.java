package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.*;
import util.SessionManager;

import java.util.ArrayList;

public class TransactionController {

    public static Response<Transaction> createTransaction(Item item, String userId) {
        if (item == null) {
            return new ResponseBuilder<Transaction>(false).withMessage("Please select an item first!").build();
        }

        Transaction transaction = new Transaction("", userId, item.getItemId());
        transaction.save();
        Wishlist.removeItem(item.getItemId());

        return new ResponseBuilder<Transaction>(true).withMessage("Successfully purchased item!").build();
    }

    public static Response<ArrayList<History>> viewHistory() {
        User user = SessionManager.getInstance().getUser();
        if (user == null) {
            return new ResponseBuilder<ArrayList<History>>(false).withMessage("User not found!").build();
        }

        ArrayList<Transaction> transactions = Transaction.viewHistory(SessionManager.getInstance().getUser().getUserId());
        if (transactions == null || transactions.isEmpty()) {
            return new ResponseBuilder<ArrayList<History>>(false).withMessage("No transactions found.").build();
        }

        ArrayList<History> histories = new ArrayList<>();
        for (Transaction transaction : transactions) {
            Item item = Item.find(transaction.getItemId());
            if (item == null) {
                continue;
            }
            histories.add(new History(transaction.getTransactionId(), item.getItemName(), item.getItemCategory(), item.getItemSize(), item.getItemPrice()));
        }
        return new ResponseBuilder<ArrayList<History>>(true).withData(histories).build();
    }

}
