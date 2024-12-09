package view;

import abstraction.Response;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import middleware.AuthMiddleware;
import model.User;
import util.SessionManager;
import util.StageManager;

public class LoginPage extends VBox {
	
	private Label title;
	private Label usernameLbl, passwordLbl, errorLbl;
	private TextField usernameTf;
	private PasswordField passwordTf;
	private Button loginBtn;
	private Hyperlink registerLink;
	
	public LoginPage() {
		middleware();
		initPage();
		addEvent();
	}
	
	private void middleware() {
		if (AuthMiddleware.loggedIn()) {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new HomePage());
		}
	}
	
	private void initPage() {
		title = new Label("Login Page");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		VBox.setMargin(title, new Insets(0, 0, 15, 0));
		
		usernameLbl = new Label("Username");
		passwordLbl = new Label("Password");
		
		usernameTf = new TextField();
		passwordTf = new PasswordField();
		
		errorLbl = new Label("");
		errorLbl.setStyle("-fx-text-fill: red");
		
		loginBtn = new Button("Login");
		registerLink = new Hyperlink("Register here.");
		
		this.getChildren().addAll(title, usernameLbl, usernameTf, passwordLbl, passwordTf, errorLbl, loginBtn, registerLink);
		
		this.setPadding(new Insets(20));
		this.setSpacing(5);
	}
	
	private void addEvent() {
		loginBtn.setOnMouseClicked(e -> {
			String username = usernameTf.getText();
			String password = passwordTf.getText();
			Response<User> response = UserController.login(username, password);
			if (response.isSuccess) {
				SessionManager session = SessionManager.getInstance();
				session.setUser(response.data);
				StageManager st = StageManager.getInstance(null);
				st.getStage().getScene().setRoot(new HomePage());
			} else {
				errorLbl.setText(response.message);
			}
		});
		
		registerLink.setOnMouseClicked(e -> {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new RegisterPage());
		});
	}

}
