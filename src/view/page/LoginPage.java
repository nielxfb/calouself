package view.page;

import abstraction.Page;
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
import view.page.user.ItemsPage;

public class LoginPage extends Page<VBox> {
	
	private Label title;
	private Label usernameLbl, passwordLbl, errorLbl;
	private TextField usernameTf;
	private PasswordField passwordTf;
	private Button loginBtn;
	private Hyperlink registerLink;
	
	public LoginPage() {
		super(new VBox());
		middleware();
		initPage();
		addEvent();
	}

	@Override
	public void middleware() {
		if (AuthMiddleware.loggedIn()) {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new HomePage().layout);
		}
	}

	@Override
	public void initPage() {
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
		
		layout.getChildren().addAll(title, usernameLbl, usernameTf, passwordLbl, passwordTf, errorLbl, loginBtn, registerLink);
		
		layout.setPadding(new Insets(20));
		layout.setSpacing(5);
	}

	@Override
	public void addEvent() {
		loginBtn.setOnAction(e -> {
			String username = usernameTf.getText().trim();
			String password = passwordTf.getText();
			Response<User> response = UserController.login(username, password);
			if (response.isSuccess) {
				SessionManager session = SessionManager.getInstance();
				User user = response.data;
				session.setUser(user);
				StageManager st = StageManager.getInstance(null);
				if (user.getRole().equals("Buyer")) {
					st.getStage().getScene().setRoot(new ItemsPage().layout);
				} else {
					st.getStage().getScene().setRoot(new HomePage().layout);
				}
			} else {
				errorLbl.setText(response.message);
			}
		});
		
		registerLink.setOnMouseClicked(e -> {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new RegisterPage().layout);
		});
	}

}
