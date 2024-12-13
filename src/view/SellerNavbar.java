package view;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.SessionManager;
import util.StageManager;

public class SellerNavbar extends MenuBar {

    public SellerNavbar() {
        Menu home = new Menu("Home");
        MenuItem homePage = new MenuItem("Home Page");
        MenuItem uploadItem = new MenuItem("Upload Item");
        MenuItem logout = new MenuItem("Logout");

        home.getItems().addAll(homePage, uploadItem, logout);

        homePage.setOnAction(e -> {
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new HomePage().layout);
        });

        uploadItem.setOnAction(e -> {
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new UploadItemPage().layout);
        });

        logout.setOnAction(e -> {
            SessionManager session = SessionManager.getInstance();
            session.logout();
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new LoginPage().layout);
        });

        getMenus().addAll(home);
    }

}
