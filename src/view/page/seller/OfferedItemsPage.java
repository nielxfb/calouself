package view.page.seller;

import abstraction.Page;
import abstraction.Response;
import controller.OfferController;
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
import model.Offer;
import util.AlertManager;
import view.component.navbar.SellerNavbar;
import view.component.offer.OfferTable;

import java.util.ArrayList;

public class OfferedItemsPage extends Page<BorderPane> {

    private OfferTable table;
    private Offer selectedOffer;
    private Button acceptBtn, declineBtn;
    private TextField reasonField;

    public OfferedItemsPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void initPage() {
        layout.setTop(new SellerNavbar());

        VBox vb = new VBox();
        vb.setPadding(new Insets(20));
        vb.setSpacing(10);

        Label title = new Label("Offered Items");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

        table = new OfferTable();
        Response<ArrayList<Offer>> response = OfferController.getBySellerId();
        if (response.isSuccess) {
            table.setItems(FXCollections.observableArrayList(response.data));
        } else {
            table.setPlaceholder(new Label(response.message));
        }

        HBox hb = new HBox();
        VBox vb2 = new VBox();

        acceptBtn = new Button("Accept");
        declineBtn = new Button("Decline");

        reasonField = new TextField();
        reasonField.setPromptText("Reason to decline");

        vb2.getChildren().addAll(declineBtn, reasonField);
        hb.getChildren().addAll(acceptBtn, vb2);

        vb2.setSpacing(10);
        hb.setSpacing(10);

        vb.getChildren().addAll(title, table, hb);
        layout.setCenter(vb);
    }

    @Override
    public void addEvent() {
        table.setOnMouseClicked(e -> {
            selectedOffer = table.getSelectionModel().getSelectedItem();
        });

        acceptBtn.setOnAction(e -> {
            Response<Offer> response = OfferController.acceptOffer(selectedOffer);
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                table.getItems().remove(selectedOffer);
                selectedOffer = null;
            } else {
                AlertManager.showError(response.message);
            }
        });

        declineBtn.setOnAction(e -> {
            Response<Offer> response = OfferController.declineOffer(selectedOffer, reasonField.getText());
            if (response.isSuccess) {
                AlertManager.showSuccess(response.message);
                table.getItems().remove(selectedOffer);
                selectedOffer = null;
            } else {
                AlertManager.showError(response.message);
            }
        });
    }
}
