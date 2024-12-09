package view;

import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import util.StageManager;

public class LoginPage extends VBox {
	
	private Label loginLbl;
	private Label usernameLbl, passwordLbl;
	private TextField usernameTf;
	private PasswordField passwordTf;
	private Button loginBtn;
	private Hyperlink registerLink;
	
	public LoginPage() {
		initPage();
		addEvent();
	}
	
	private void initPage() {
		loginLbl = new Label("Login Page");
		
		usernameLbl = new Label("Username");
		passwordLbl = new Label("Password");
		
		usernameTf = new TextField();
		passwordTf = new PasswordField();
		
		loginBtn = new Button("Login");
		registerLink = new Hyperlink("Register here.");
	}
	
	private void addEvent() {
		loginBtn.setOnMouseClicked(e -> {
			// Implement login logic.
		});
		
		registerLink.setOnMouseClicked(e -> {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new RegisterPage());
		});
	}

}
