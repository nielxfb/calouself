package view;

import abstraction.Page;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import util.StageManager;

public class UploadItemPage extends Page<BorderPane> {

    private UploadItemForm form;

    public UploadItemPage() {
        super(new BorderPane());
        middleware();
        initPage();
        addEvent();
    }

    @Override
    public void middleware() {
        if (!AuthMiddleware.loggedIn()) {
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new LoginPage().layout);
        }
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
        form = new UploadItemForm("Upload");

        HBox hb = new HBox();
        hb.getChildren().addAll(table, form);
        vb.getChildren().add(hb);
        vb.setSpacing(10);
        hb.setSpacing(25);
    }

    @Override
    public void addEvent() {
        form.btn.setOnAction(e -> {

        });
    }
}
