package view.page.user;

import abstraction.Page;
import abstraction.Response;
import controller.TransactionController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.History;
import view.component.history.HistoryTable;
import view.component.navbar.UserNavbar;

import java.util.ArrayList;

public class TransactionHistoryPage extends Page<BorderPane> {

    public TransactionHistoryPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void initPage() {
        layout.setTop(new UserNavbar());

        Label title = new Label("Transaction History");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        VBox vb = new VBox();
        layout.setCenter(vb);

        vb.setSpacing(10);
        vb.setPadding(new Insets(20));

        HistoryTable table = new HistoryTable();

        Response<ArrayList<History>> response = TransactionController.viewHistory();
        if (response.isSuccess) {
            table.setItems(FXCollections.observableArrayList(response.data));
        } else {
            table.setPlaceholder(new Label(response.message));
        }

        vb.getChildren().addAll(title, table);
        layout.setCenter(vb);
    }

    @Override
    public void addEvent() {

    }
}
