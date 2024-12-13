package view;

import abstraction.Page;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import model.Item;
import util.StageManager;

public class HomePage extends Page<BorderPane> {

	private String role;
	private Label errorLbl;
	private Button delBtn;
	private Item selectedItem;
	private ItemTable table;

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
		Label title;
		if (role.equals("Seller")) {
			title = new Label("Upload Item");
			layout.setTop(new SellerNavbar());
		} else {
			title = new Label("Home Page");
		}
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		VBox.setMargin(title, new javafx.geometry.Insets(0, 0, 15, 0));

		VBox vb = new VBox();
		layout.setCenter(vb);

		vb.getChildren().addAll(title);
		vb.setPadding(new javafx.geometry.Insets(20));

		errorLbl = new Label("");
		errorLbl.setStyle("-fx-text-fill: red;");
		errorLbl.setVisible(false);
		vb.getChildren().add(errorLbl);

		table = new ItemTable();

		if (!table.errorMessage.trim().isEmpty()) {
			errorLbl.setText(table.errorMessage);
		} else {
			if (role.equals("User"))
				vb.getChildren().add(table);
			else if (role.equals("Seller")) {
				HBox hb = new HBox();
				VBox vb2 = new VBox();

				vb2.getChildren().add(new UploadItemForm());
				hb.getChildren().addAll(table, vb2);

				delBtn = new Button("Delete Selected Item");
				vb2.getChildren().add(delBtn);

				hb.setSpacing(25);
				vb.getChildren().add(hb);
			}
		}
	}

	@Override
	public void addEvent() {
		table.setOnMouseClicked(e -> selectedItem = table.getSelectionModel().getSelectedItem());

		delBtn.setOnAction(e -> {
			errorLbl.setText("");
			errorLbl.setVisible(false);
			if (selectedItem == null) {
				errorLbl.setText("Please select an item first before deleting!");
				errorLbl.setVisible(true);
			}
		});
	}

}
