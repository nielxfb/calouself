package view.component.offer;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Offer;

public class OfferTable extends TableView<Offer> {

    public OfferTable() {
        TableColumn<Offer, String> nameCol = new TableColumn<>("Name");
        TableColumn<Offer, Integer> priceCol = new TableColumn<>("Original Price");
        TableColumn<Offer, Integer> offerCol = new TableColumn<>("Offered Price");
        TableColumn<Offer, String> userCol = new TableColumn<>("Offerer");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        offerCol.setCellValueFactory(new PropertyValueFactory<>("offerPrice"));
        userCol.setCellValueFactory(new PropertyValueFactory<>("username"));

        getColumns().addAll(nameCol, priceCol, offerCol, userCol);
    }
}
