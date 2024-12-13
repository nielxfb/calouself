package view.component.navbar;

import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.SessionManager;
import util.StageManager;
import view.page.*;
import view.page.seller.DeleteItemPage;
import view.page.seller.EditItemPage;
import view.page.seller.UploadItemPage;

public class SellerNavbar extends MenuBar {

    public SellerNavbar() {
        Menu home = new Menu("Home");
        MenuItem homePage = new MenuItem("Home Page");
        MenuItem logout = new MenuItem("Logout");
        home.getItems().addAll(homePage, logout);

        Menu item = new Menu("Item");
        MenuItem uploadItem = new MenuItem("Upload Item");
        MenuItem editItem = new MenuItem("Edit Item");
        MenuItem deleteItem = new MenuItem("Delete Item");
        item.getItems().addAll(uploadItem, editItem, deleteItem);

        StageManager st = StageManager.getInstance(null);

        homePage.setOnAction(e -> {
            st.getStage().getScene().setRoot(new HomePage().layout);
        });

        uploadItem.setOnAction(e -> {
            st.getStage().getScene().setRoot(new UploadItemPage().layout);
        });

        editItem.setOnAction(e -> {
            st.getStage().getScene().setRoot(new EditItemPage().layout);
        });

        deleteItem.setOnAction(e -> {
            st.getStage().getScene().setRoot(new DeleteItemPage().layout);
        });

        logout.setOnAction(e -> {
            SessionManager session = SessionManager.getInstance();
            session.logout();
            st.getStage().getScene().setRoot(new LoginPage().layout);
        });

        getMenus().addAll(home, item);
    }

}
