package controller;

import abstraction.Response;
import builder.ResponseBuilder;
import model.Item;
import model.Offer;
import util.SessionManager;

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

        Offer newOffer = new Offer("", SessionManager.getInstance().getUser().getUserId(), item.getItemId(), offer);
        newOffer.save();
        return new ResponseBuilder<Offer>(true).withMessage("Offer created successfully! Please wait for it to be approved.").build();
    }

}
