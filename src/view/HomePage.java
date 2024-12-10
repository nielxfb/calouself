package view;

import abstraction.Page;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import util.StageManager;

public class HomePage extends Page<VBox> {

	private Label title;

	public HomePage() {
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
		title = new Label("Home Page");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		VBox.setMargin(title, new javafx.geometry.Insets(0, 0, 15, 0));

		layout.getChildren().addAll(title);
		layout.setPadding(new javafx.geometry.Insets(20));
	}

	@Override
	public void addEvent() {

	}

}
