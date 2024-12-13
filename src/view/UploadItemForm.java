package view;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class UploadItemForm extends VBox {

    private TextField nameField;
    private TextField categoryField;
    private TextField sizeField;
    private TextField priceField;
    private Button uploadBtn;

    public UploadItemForm() {
        Label nameLbl = new Label("Name");
        Label categoryLbl = new Label("Category");
        Label sizeLbl = new Label("Size");
        Label priceLbl = new Label("Price");

        nameField = new TextField();
        categoryField = new TextField();
        sizeField = new TextField();
        priceField = new TextField();

        uploadBtn = new Button("Upload");

        getChildren().addAll(nameLbl, nameField, categoryLbl, categoryField, sizeLbl, sizeField, priceLbl, priceField, uploadBtn);
        setSpacing(5);

        addEvent();
    }

    private void addEvent() {
        uploadBtn.setOnAction(e -> {
            String name = nameField.getText();
            String category = categoryField.getText();
            String size = sizeField.getText();
            String price = priceField.getText();

        });
    }

}
