package view.page.user;

import abstraction.Page;
import abstraction.Response;
import controller.ItemController;
import controller.OfferController;
import controller.TransactionController;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import model.Item;
import model.Offer;
import model.Transaction;
import model.Wishlist;
import util.AlertManager;
import util.StageManager;
import view.component.item.ItemTable;
import view.component.navbar.UserNavbar;
import view.page.LoginPage;

import java.util.ArrayList;

public class ItemsPage extends Page<BorderPane> {

    private ItemTable table;
    private Item selectedItem;
    private Button makeOfferBtn, purchaseBtn, addToWishlistBtn;
    private TextField offerField;

    public ItemsPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void initPage() {
        layout.setTop(new UserNavbar());

        VBox vb = new VBox();
        vb.setSpacing(10);
        vb.setPadding(new Insets(20));

        Label title = new Label("Home Page");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        table = new ItemTable();
        Response<ArrayList<Item>> response = ItemController.getApproved();
        if (response.isSuccess) {
            table.setItems(FXCollections.observableArrayList(response.data));
        } else {
            table.setPlaceholder(new Label(response.message));
        }

        HBox hb = new HBox();
        VBox vb2 = new VBox();

        purchaseBtn = new Button("Purchase Item");
        makeOfferBtn = new Button("Make Offer on Item");
        addToWishlistBtn = new Button("Add to Wishlist");

        offerField = new TextField();
        offerField.setPromptText("Enter an offer price");

        vb2.getChildren().addAll(makeOfferBtn, offerField);
        vb2.setSpacing(10);

        hb.getChildren().addAll(addToWishlistBtn, purchaseBtn, vb2);
        hb.setSpacing(10);

        vb.getChildren().addAll(title, table, hb);

        layout.setCenter(vb);
    }

    @Override
    public void addEvent() {
        table.setOnMouseClicked(e -> {
            selectedItem = table.getSelectionModel().getSelectedItem();
        });

        purchaseBtn.setOnAction(e -> {
            Boolean purchase = AlertManager.showPopUp("Are you sure you want to purchase this item?");
            if (!purchase) {
                return;
            }
            Response<Transaction> response = TransactionController.createTransaction(selectedItem);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                selectedItem = null;
            } else {
                AlertManager.showError(response.message);
            }
        });

        makeOfferBtn.setOnAction(e -> {
            Response<Offer> response = OfferController.createOffer(selectedItem, offerField.getText());
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                selectedItem = null;
            } else {
                AlertManager.showError(response.message);
            }
        });

        addToWishlistBtn.setOnAction(e -> {
            Response<Wishlist> response = WishlistController.addToWishlist(selectedItem);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                selectedItem = null;
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
