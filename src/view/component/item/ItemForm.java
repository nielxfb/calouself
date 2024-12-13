package view.component.item;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class ItemForm extends VBox {

    public TextField nameField;
    public TextField categoryField;
    public TextField sizeField;
    public TextField priceField;
    public Button btn;

    public ItemForm(String buttonText) {
        Label nameLbl = new Label("Name");
        Label categoryLbl = new Label("Category");
        Label sizeLbl = new Label("Size");
        Label priceLbl = new Label("Price");

        nameField = new TextField();
        categoryField = new TextField();
        sizeField = new TextField();
        priceField = new TextField();

        btn = new Button(buttonText);

        getChildren().addAll(nameLbl, nameField, categoryLbl, categoryField, sizeLbl, sizeField, priceLbl, priceField, btn);
        setSpacing(5);
    }

}
