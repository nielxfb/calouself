package view.component.navbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.SessionManager;
import util.StageManager;
import view.page.HomePage;
import view.page.LoginPage;
import view.page.admin.ItemsPage;

public class AdminNavbar extends MenuBar {

    public AdminNavbar() {
        Menu home = new Menu("Home");
        MenuItem homePage = new MenuItem("Home Page");
        MenuItem logout = new MenuItem("Logout");
        home.getItems().addAll(homePage, logout);

        Menu item = new Menu("Item");
        MenuItem viewRequest = new MenuItem("View Requested Items");
        item.getItems().add(viewRequest);

        StageManager st = StageManager.getInstance(null);

        homePage.setOnAction(e -> {
            st.getStage().getScene().setRoot(new HomePage().layout);
        });

        logout.setOnAction(e -> {
            SessionManager session = SessionManager.getInstance();
            session.logout();
            st.getStage().getScene().setRoot(new LoginPage().layout);
        });

        viewRequest.setOnAction(e -> {
            st.getStage().getScene().setRoot(new ItemsPage().layout);
        });

        getMenus().addAll(home, item);
    }

}
