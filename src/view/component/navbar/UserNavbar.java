package view.component.navbar;



import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import util.StageManager;
import view.page.HomePage;
import view.page.user.ViewItemPage;
import view.page.user.WishlistPage;

public class UserNavbar extends MenuBar{

	public UserNavbar() {
		// TODO Auto-generated constructor stub
		Menu home = new Menu("Home");
		MenuItem homePage = new MenuItem("Home Page");
		MenuItem logout = new MenuItem("Logout");
		home.getItems().addAll(homePage, logout);
		
		Menu item = new Menu("Item");
		MenuItem viewItem = new MenuItem("View All Items");
		MenuItem wishlist = new MenuItem("Wishlist");
		MenuItem purchaseHistory = new MenuItem("Purchase History");
		item.getItems().addAll(viewItem, wishlist, purchaseHistory);
		
		StageManager st = StageManager.getInstance(null);
		
		homePage.setOnAction(e -> {
			st.getStage().getScene().setRoot(new HomePage().layout);
		});
		
		viewItem.setOnAction(e -> {
			st.getStage().getScene().setRoot(new ViewItemPage().layout);
		});
		
		wishlist.setOnAction(e -> {
			st.getStage().getScene().setRoot(new WishlistPage().layout);
		});
		
		getMenus().addAll(home, item);
		
	}

}
