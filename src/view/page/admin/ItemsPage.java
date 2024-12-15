package view.page.admin;

import abstraction.Page;
import abstraction.Response;
import controller.ItemController;
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
import util.AlertManager;
import util.StageManager;
import view.component.item.ItemTable;
import view.component.navbar.AdminNavbar;
import view.page.LoginPage;

import java.util.ArrayList;

public class ItemsPage extends Page<BorderPane> {

    private Item selectedItem;
    private ItemTable table;
    private Button approveBtn, declineBtn;
    private TextField reasonField;

    public ItemsPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void initPage() {
        Label title = new Label("View Requested Items");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        layout.setTop(new AdminNavbar());

        VBox vb = new VBox();

        table = new ItemTable();

        approveBtn = new Button("Approve Item");
        declineBtn = new Button("Decline Item");

        reasonField = new TextField();
        reasonField.setPromptText("Reason to decline");

        VBox vb2 = new VBox();
        vb2.getChildren().addAll(declineBtn, reasonField);

        HBox hb = new HBox();
        hb.getChildren().addAll(approveBtn, vb2);

        vb.getChildren().addAll(title, table, hb);
        layout.setCenter(vb);

        vb.setPadding(new Insets(20));
        vb.setSpacing(10);
        vb2.setSpacing(10);
        hb.setSpacing(10);

        getData();
    }

    private void getData() {
        table.setItems(null);
        Response<ArrayList<Item>> response = ItemController.getPending();
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


        approveBtn.setOnAction(e -> {
            Response<Boolean> response = ItemController.approveItem(selectedItem);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                getData();
            } else {
                AlertManager.showError(response.message);
            }
        });

        declineBtn.setOnAction(e -> {
            String reason = reasonField.getText();

            Response<Boolean> response = ItemController.declineItem(selectedItem, reason);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                getData();
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
