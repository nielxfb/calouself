package view.page.seller;

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
import view.component.item.ItemForm;
import view.component.item.ItemTable;
import view.component.navbar.SellerNavbar;
import view.page.LoginPage;

import java.util.ArrayList;

public class UploadItemPage extends Page<BorderPane> {

    private ItemForm form;

    public UploadItemPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void initPage() {
        Label title = new Label("Upload Item");
        layout.setTop(new SellerNavbar());

        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        VBox vb = new VBox();
        layout.setCenter(vb);

        vb.getChildren().add(title);

        vb.setPadding(new Insets(20));

        ItemTable table = new ItemTable();
        form = new ItemForm("Upload");

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
        form.btn.setOnAction(e -> {
            String name = form.nameField.getText();
            String category = form.categoryField.getText();
            String size = form.sizeField.getText();
            String price = form.priceField.getText();

            Response<Item> response = ItemController.uploadItem(name, size, category, price);
            if (response.isSuccess) {
                AlertManager.showSuccess("Successfully uploaded item! Please wait for it to be approved.");
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
