package view.component.navbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.SessionManager;
import util.StageManager;
import view.page.LoginPage;
import view.page.user.ItemsPage;
import view.page.user.WishlistPage;

public class UserNavbar extends MenuBar {

    public UserNavbar() {
        Menu home = new Menu("Home");
        MenuItem homePage = new MenuItem("Home Page");
        MenuItem logout = new MenuItem("Logout");
        home.getItems().addAll(homePage, logout);

        Menu item = new Menu("Item");
        MenuItem wishlist = new MenuItem("View Wishlist");
        item.getItems().addAll(wishlist);

        StageManager st = StageManager.getInstance(null);

        homePage.setOnAction(e -> {
            st.getStage().getScene().setRoot(new ItemsPage().layout);
        });

        logout.setOnAction(e -> {
            SessionManager session = SessionManager.getInstance();
            session.logout();
            st.getStage().getScene().setRoot(new LoginPage().layout);
        });

        wishlist.setOnAction(e -> {
            st.getStage().getScene().setRoot(new WishlistPage().layout);
        });

        getMenus().addAll(home, item);
    }

}
