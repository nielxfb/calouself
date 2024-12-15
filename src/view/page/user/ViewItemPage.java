package view.page.user;

import java.util.ArrayList;

import abstraction.Page;
import abstraction.Response;
import controller.ItemController;
import controller.WishlistController;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import middleware.AuthMiddleware;
import model.Item;
import model.Wishlist;
import util.AlertManager;
import util.SessionManager;
import util.StageManager;
import view.component.item.ItemForm;
import view.component.item.ItemTable;
import view.component.navbar.UserNavbar;
import view.page.LoginPage;

public class ViewItemPage extends Page<BorderPane>{
	
	private ItemTable table;
    private Item selectedItem;
    private Button wishListBtn;
    private Button buyBtn;

	public ViewItemPage() {
		// TODO Auto-generated constructor stub
		super(new BorderPane());
		middleware();
		initPage();
		addEvent();
	}

	@Override
	public void middleware() {
		// TODO Auto-generated method stub
		if (!AuthMiddleware.loggedIn()) {
            StageManager st = StageManager.getInstance(null);
            st.getStage().getScene().setRoot(new LoginPage().layout);
        }
	}

	@Override
	public void initPage() {
		// TODO Auto-generated method stub
		Label title = new Label("All Items");
		layout.setTop(new UserNavbar());
		
		VBox vb = new VBox();
		layout.setCenter(vb);
		vb.getChildren().add(title);
		vb.setPadding(new Insets(20));
		
		table= new ItemTable();
		wishListBtn = new Button("Add to wishlist");
		buyBtn = new Button("Buy");
		
		Response<ArrayList<Item>> response = ItemController.getApproved();
	    if (response.isSuccess) {
	        table.setItems(FXCollections.observableArrayList(response.data));
	    } else {
	        table.setPlaceholder(new Label(response.message));
	    }
	    

	    vb.getChildren().addAll(table, wishListBtn, buyBtn);

	    layout.setCenter(vb);
		
	}

	@Override
	public void addEvent() {
		// TODO Auto-generated method stub
		table.setOnMouseClicked(e -> {
            selectedItem = table.getSelectionModel().getSelectedItem();
        });
		wishListBtn.setOnAction(e -> {
			if(selectedItem != null) {
				if(AuthMiddleware.loggedIn()) {
					String userId = SessionManager.getInstance().getUser().getUserId();
					Response<Wishlist> response = WishlistController.addWishlist(selectedItem.getItemId(), userId);
					if(response.isSuccess) {
						AlertManager.showSuccess(response.message);

		                selectedItem = null;
		            } else {
		                AlertManager.showError(response.message);
					}
				}
			}
			
		});
	}

}
