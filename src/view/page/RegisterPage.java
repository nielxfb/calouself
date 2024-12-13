package view.page;

import abstraction.Page;
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
import middleware.AuthMiddleware;
import model.User;
import util.StageManager;

public class RegisterPage extends Page<VBox> {

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
		title = new Label("Register");
		title.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
		VBox.setMargin(title, new Insets(0, 0, 15, 0));

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

		layout.getChildren().addAll(title, usernameLb, usernameTf, passLb, passTf, phoneLb, phoneTf, addressLb, addressTf,
				roleLb, buyerBtn, sellerBtn, errorLbl, registerBtn, loginLink);

		layout.setPadding(new Insets(20));
		layout.setSpacing(5);
	}

	@Override
	public void addEvent() {
		registerBtn.setOnAction(e -> {
			String username = usernameTf.getText().trim();
			String password = passTf.getText();
			String phoneNumber = phoneTf.getText().trim();
			String address = addressTf.getText().trim();
			String role = roleGroup.getSelectedToggle() != null
					? ((RadioButton) roleGroup.getSelectedToggle()).getText()
					: "";
					
			Response<User> response = UserController.register(username, password, phoneNumber, address, role);
			if (response.isSuccess) {
				StageManager st = StageManager.getInstance(null);
				st.getStage().getScene().setRoot(new LoginPage().layout);
			} else {
				errorLbl.setText(response.message);
			}
		});
		
		loginLink.setOnMouseClicked(e -> {
			StageManager st = StageManager.getInstance(null);
			st.getStage().getScene().setRoot(new LoginPage().layout);
		});
	}
	
}
