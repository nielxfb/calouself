package view.page.seller;

import abstraction.Page;
import abstraction.Response;
import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import model.Item;
import util.AlertManager;
import util.StageManager;
import view.component.item.ItemTable;
import view.component.navbar.SellerNavbar;
import view.page.LoginPage;

import java.util.ArrayList;

public class DeleteItemPage extends Page<BorderPane> {

    private ItemTable table;
    private Item selectedItem;
    private Button delBtn;

    public DeleteItemPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void middleware() {
        if (!AuthMiddleware.loggedIn()) {
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new LoginPage().layout);
        }
    }

    @Override
    public void initPage() {
        Label title = new Label("Delete Item");
        layout.setTop(new SellerNavbar());

        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        VBox vb = new VBox();
        layout.setCenter(vb);

        vb.getChildren().add(title);

        vb.setPadding(new Insets(20));

        table = new ItemTable();
        delBtn = new Button("Delete");

        vb.getChildren().addAll(table, delBtn);
        vb.setSpacing(10);

        Response<ArrayList<Item>> response = ItemController.getApproved();
        if (response.isSuccess) {
            table.setItems(FXCollections.observableArrayList(response.data));
        } else {
            table.setPlaceholder(new Label(response.message));
        }
    }

    @Override
    public void addEvent() {
        table.setOnMouseClicked(e -> {
            selectedItem = table.getSelectionModel().getSelectedItem();
        });

        delBtn.setOnAction(e -> {
            Response<Item> response = ItemController.deleteItem(selectedItem);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                table.getItems().remove(selectedItem);
                selectedItem = null;
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
