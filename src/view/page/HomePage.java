package view.page;

import abstraction.Page;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import util.StageManager;
import view.component.navbar.AdminNavbar;
import view.component.navbar.SellerNavbar;

public class HomePage extends Page<BorderPane> {

    String role;

    public HomePage() {
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

        role = AuthMiddleware.getRole();
    }

    @Override
    public void initPage() {
        Label title = new Label("Home Page");
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
        if (role.equals("Seller")) layout.setTop(new SellerNavbar());
        else if (role.equals("Admin")) layout.setTop(new AdminNavbar());

        Label description = new Label("Welcome to calouself. Please select an option from the menu above.");

        VBox vb = new VBox();
        vb.getChildren().addAll(title, description);

        vb.setAlignment(Pos.CENTER);
        vb.setSpacing(10);

        layout.setCenter(vb);
    }

    @Override
    public void addEvent() {

    }
}
