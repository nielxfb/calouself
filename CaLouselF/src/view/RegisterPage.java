package view;

import abstraction.Response;
import controller.UserController;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import model.User;
import util.StageManager;

public class RegisterPage extends VBox {

	private Label title;
	private Label usernameLb, passLb, phoneLb, addressLb, roleLb;
	private TextField usernameTf, phoneTf, addressTf;
	private PasswordField passTf;
	private ToggleGroup roleGroup;
	private RadioButton sellerBtn, buyerBtn;
	private Hyperlink loginLink;
	private Button registerBtn;
	private Label errorLbl;

	public RegisterPage() {
		initPage();
		addEvent();
	}

	public void initPage() {
		title = new Label("Register");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));

		usernameLb = new Label("Username");
		passLb = new Label("Password");
		phoneLb = new Label("Phone Number");
		addressLb = new Label("Address");
		roleLb = new Label("Role");

		usernameTf = new TextField();
		passTf = new PasswordField();
		phoneTf = new TextField();
		addressTf = new TextField();

		roleGroup = new ToggleGroup();
		buyerBtn = new RadioButton("Buyer");
		sellerBtn = new RadioButton("Seller");
		
		loginLink = new Hyperlink("Login here.");

		errorLbl = new Label("");
		errorLbl.setStyle("-fx-text-fill: red;");
		
		registerBtn = new Button("Register");

		roleGroup.getToggles().addAll(buyerBtn, sellerBtn);

		this.getChildren().addAll(title, usernameLb, usernameTf, passLb, passTf, phoneLb, phoneTf, addressLb, addressTf,
				roleLb, buyerBtn, sellerBtn, errorLbl, registerBtn, loginLink);

		this.setPadding(new Insets(20));
		this.setSpacing(5);
	}
	
	private void addEvent() {
		registerBtn.setOnMouseClicked(e -> {
			String username = usernameTf.getText();
			String password = passTf.getText();
			String phoneNumber = phoneTf.getText();
			String address = addressTf.getText();
			String role = roleGroup.getSelectedToggle() != null
					? ((RadioButton) roleGroup.getSelectedToggle()).getText()
					: "";
					
			Response<User> response = UserController.register(username, password, phoneNumber, address, role);
			if (response.isSuccess) {
				StageManager st = StageManager.getInstance(null);
				st.getStage().getScene().setRoot(new LoginPage());
			} else {
				errorLbl.setText(response.message);
			}
		});
		
		loginLink.setOnMouseClicked(e -> {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new LoginPage());
		});
	}
	
}
