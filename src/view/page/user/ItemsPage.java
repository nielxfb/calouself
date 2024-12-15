package view.page.user;

import abstraction.Page;
import abstraction.Response;
import controller.ItemController;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import model.Item;
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

        purchaseBtn = new Button("Purchase Item");
        makeOfferBtn = new Button("Make Offer on Item");
        addToWishlistBtn = new Button("Add to Wishlist");

        hb.getChildren().addAll(purchaseBtn, makeOfferBtn, addToWishlistBtn);
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
            if (selectedItem == null) {
                AlertManager.showError("Please select an item first!");
                return;
            }
            StageManager st = StageManager.getInstance(null);
//            st.getStage().getScene().setRoot(new PurchaseItemPage(selectedItem).layout);
        });

        makeOfferBtn.setOnAction(e -> {
            if (selectedItem == null) {
                AlertManager.showError("Please select an item first!");
                return;
            }
            StageManager st = StageManager.getInstance(null);
//            st.getStage().getScene().setRoot(new MakeOfferPage(selectedItem).layout);
        });

        addToWishlistBtn.setOnAction(e -> {
            if (selectedItem == null) {
                AlertManager.showError("Please select an item first!");
                return;
            }

            Response<Wishlist> response = WishlistController.addToWishlist(selectedItem.getItemId());
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                selectedItem = null;
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
