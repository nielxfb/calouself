package view.page.user;

import abstraction.Page;
import abstraction.Response;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.Item;
import model.Wishlist;
import util.AlertManager;
import util.SessionManager;
import view.component.item.ItemTable;
import view.component.navbar.UserNavbar;

import java.util.ArrayList;

public class WishlistPage extends Page<BorderPane> {

    private ItemTable table;
    private Item selectedItem;
    private Button removeFromWishlistBtn;

    public WishlistPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void initPage() {
        layout.setTop(new UserNavbar());

        VBox vb = new VBox();
        vb.setPadding(new Insets(20));
        vb.setSpacing(10);

        Label title = new Label(SessionManager.getInstance().getUser().getUsername() + "'s Wishlist");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        table = new ItemTable();

        Response<ArrayList<Wishlist>> response = WishlistController.getWishlist();
        if (response.isSuccess) {
            ArrayList<Item> items = new ArrayList<>();
            for (Wishlist wishlist : response.data) {
                items.add(wishlist.getItem());
            }
            table.setItems(FXCollections.observableArrayList(items));
        } else {
            table.setPlaceholder(new Label(response.message));
        }

        removeFromWishlistBtn = new Button("Remove from wishlist");

        vb.getChildren().addAll(title, table, removeFromWishlistBtn);
        layout.setCenter(vb);
    }

    @Override
    public void addEvent() {
        table.setOnMouseClicked(e -> {
            selectedItem = table.getSelectionModel().getSelectedItem();
        });

        removeFromWishlistBtn.setOnAction(e -> {
            Response<Wishlist> response = WishlistController.removeWishlist(selectedItem);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                table.getItems().remove(selectedItem);
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
