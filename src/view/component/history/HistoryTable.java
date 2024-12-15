package view.component.history;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.History;

public class HistoryTable extends TableView<History> {

    public HistoryTable() {
        TableColumn<History, String> idCol = new TableColumn<>("Transaction ID");
        TableColumn<History, String> nameCol = new TableColumn<>("Item");
        TableColumn<History, String> categoryCol = new TableColumn<>("Category");
        TableColumn<History, String> sizeCol = new TableColumn<>("Size");
        TableColumn<History, Integer> priceCol = new TableColumn<>("Price");

        idCol.setCellValueFactory(new PropertyValueFactory<>("transactionId"));
        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        getColumns().addAll(idCol, nameCol, categoryCol, sizeCol, priceCol);
    }

}
