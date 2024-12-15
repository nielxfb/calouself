package view.component.item;

import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

public class ItemTable extends TableView<Item> {

    public ItemTable() {
        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        TableColumn<Item, String> categoryCol = new TableColumn<>("Category");
        TableColumn<Item, String> sizeCol = new TableColumn<>("Size");
        TableColumn<Item, Integer> priceCol = new TableColumn<>("Price");
        TableColumn<Item, String> statusCol = new TableColumn<>("Status");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));
        statusCol.setCellValueFactory(new PropertyValueFactory<>("itemStatus"));

        getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol, statusCol);
    }
    
   
}
