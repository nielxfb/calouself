package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.SessionManager;
import util.StageManager;

public class SellerNavbar extends MenuBar {

    public SellerNavbar() {
        Menu home = new Menu("Home");
        MenuItem uploadItem = new MenuItem("Upload Item");
        MenuItem logout = new MenuItem("Logout");

        home.getItems().addAll(uploadItem, logout);

        logout.setOnAction(e -> {
            SessionManager session = SessionManager.getInstance();
            session.logout();
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new LoginPage().layout);
        });

        getMenus().addAll(home);
    }

}
