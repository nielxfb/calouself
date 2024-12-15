package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import model.Offer;
import model.Transaction;
import model.User;
import util.SessionManager;

import java.util.ArrayList;

public class OfferController {

    public static Response<Offer> createOffer(Item item, String offerPrice) {
        String error = "";
        if (item == null) {
            error = "Please select an item first!";
        } else if (offerPrice.trim().isEmpty()) {
            error = "Offer price must not be empty!";
        }

        if (!error.isEmpty()) {
            return new ResponseBuilder<Offer>(false).withMessage(error).build();
        }

        Integer offer = -1;
        try {
            offer = Integer.parseInt(offerPrice);
        } catch (Exception e) {
            return new ResponseBuilder<Offer>(false).withMessage("Offer price must be a number!").build();
        }

        if (offer < 0) {
            return new ResponseBuilder<Offer>(false).withMessage("Offer price must be a positive number!").build();
        }

        Integer highestOffer = Offer.getHighestOffer(item.getItemId());
        if (highestOffer != null && offer <= highestOffer) {
            return new ResponseBuilder<Offer>(false).withMessage("Offer price must be higher than the highest offer!").build();
        }

        User user = SessionManager.getInstance().getUser();

        Offer newOffer = new Offer("", user.getUserId(), item.getItemId(), offer);
        newOffer.save();
        return new ResponseBuilder<Offer>(true).withMessage("Offer created successfully! Please wait for it to be approved.").build();
    }

    public static Response<ArrayList<Offer>> getBySellerId() {
        User user = SessionManager.getInstance().getUser();
        if (user == null) {
            return new ResponseBuilder<ArrayList<Offer>>(false).withMessage("User not found!").build();
        }

        ArrayList<Offer> offers = Offer.getBySellerId(user.getUserId());
        if (offers == null || offers.isEmpty()) {
            return new ResponseBuilder<ArrayList<Offer>>(false).withMessage("No offers found.").build();
        }

        for (Offer offer : offers) {
            offer.setUser(User.find(offer.getUserId()));
            offer.setItem(Item.find(offer.getItemId()));
        }

        return new ResponseBuilder<ArrayList<Offer>>(true).withData(offers).build();
    }

    public static Response<Offer> acceptOffer(Offer offer) {
        if (offer == null) {
            return new ResponseBuilder<Offer>(false).withMessage("Please select an offer!").build();
        }

        offer.remove();
        Response<Transaction> response = TransactionController.createTransaction(offer.getItem(), offer.getUserId());
        if (response.isSuccess) {
            return new ResponseBuilder<Offer>(true).withMessage("Offer accepted successfully!").build();
        } else {
            return new ResponseBuilder<Offer>(false).withMessage(response.message).build();
        }
    }

    public static Response<Offer> declineOffer(Offer offer, String reason) {
        if (offer == null) {
            return new ResponseBuilder<Offer>(false).withMessage("Please select an offer!").build();
        }

        if (reason.trim().isEmpty()) {
            return new ResponseBuilder<Offer>(false).withMessage("Reason must not be empty!").build();
        }

        offer.remove();
        return new ResponseBuilder<Offer>(true).withMessage("Offer declined successfully!").build();
    }

}
