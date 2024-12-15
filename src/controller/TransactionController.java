package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.*;
import util.SessionManager;

import java.util.ArrayList;

public class TransactionController {

    /**
     * Creates a transaction for the specified item and user.
     *
     * @param item the item to be purchased
     * @param userId the ID of the user making the purchase
     * @return a response containing the created transaction or an error message
     */
    public static Response<Transaction> createTransaction(Item item, String userId) {
        if (item == null) {
            return new ResponseBuilder<Transaction>(false).withMessage("Please select an item first!").build();
        }

        Transaction transaction = new Transaction("", userId, item.getItemId());
        transaction.save();
        Wishlist.removeItem(item.getItemId());

        return new ResponseBuilder<Transaction>(true).withMessage("Successfully purchased item!").build();
    }

    /**
     * Retrieves the transaction history for the current user.
     *
     * @return a response containing a list of transaction histories or an error message
     */
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