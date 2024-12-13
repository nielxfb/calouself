package view;

import abstraction.Response;
import controller.ItemController;
import javafx.collections.FXCollections;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Item;

import java.util.ArrayList;

public class ItemTable extends TableView<Item> {

    public ItemTable() {
        TableColumn<Item, String> nameCol = new TableColumn<>("Name");
        TableColumn<Item, String> categoryCol = new TableColumn<>("Category");
        TableColumn<Item, String> sizeCol = new TableColumn<>("Size");
        TableColumn<Item, Integer> priceCol = new TableColumn<>("Price");

        nameCol.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("itemCategory"));
        sizeCol.setCellValueFactory(new PropertyValueFactory<>("itemSize"));
        priceCol.setCellValueFactory(new PropertyValueFactory<>("itemPrice"));

        getColumns().addAll(nameCol, categoryCol, sizeCol, priceCol);

        getData();
    }

    private void getData() {
        Response<ArrayList<Item>> response = ItemController.getAll();
        if (response.isSuccess) {
            this.setItems(FXCollections.observableArrayList(response.data));
        }
    }
}
