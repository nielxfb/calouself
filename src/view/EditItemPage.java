package view;

import abstraction.Page;
import abstraction.Response;
import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import model.Item;
import util.AlertManager;
import util.StageManager;

import java.util.ArrayList;

public class EditItemPage extends Page<BorderPane> {

    private ItemTable table;
    private ItemForm form;
    private Item selectedItem;

    public EditItemPage() {
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
        Label title = new Label("Edit Item");
        layout.setTop(new SellerNavbar());

        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        VBox vb = new VBox();
        layout.setCenter(vb);

        vb.getChildren().add(title);

        vb.setPadding(new Insets(20));

        table = new ItemTable();
        form = new ItemForm("Edit");

        HBox hb = new HBox();
        hb.getChildren().addAll(table, form);
        vb.getChildren().add(hb);
        vb.setSpacing(10);
        hb.setSpacing(25);

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
            form.nameField.setText(selectedItem.getItemName());
            form.sizeField.setText(selectedItem.getItemSize());
            form.categoryField.setText(selectedItem.getItemCategory());
            form.priceField.setText(String.valueOf(selectedItem.getItemPrice()));
        });

        form.btn.setOnAction(e -> {
            if (selectedItem == null) {
                AlertManager.showError("Please select an item to edit!");
                return;
            }

            String name = form.nameField.getText();
            String category = form.categoryField.getText();
            String size = form.sizeField.getText();
            String price = form.priceField.getText();

            Response<Item> response = ItemController.editItem(selectedItem.getItemId(), name, size, category, price, selectedItem.getItemStatus());
            if (response.isSuccess) {
                AlertManager.showSuccess("Item updated successfully");
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
