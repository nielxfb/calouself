package view;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

public class RegisterPage extends VBox {

	private Label title;
	private Label usernameLb, passLb, phoneLb, addressLb, roleLb;
	private TextField usernameTf, phoneTf, addressTf;
	private PasswordField passTf;
	private ToggleGroup roleGroup;
	private RadioButton sellerBtn, buyerBtn;
	private Button registerBtn;

	public RegisterPage() {
		init();
		addEvent();
	}

	public void init() {
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

		registerBtn = new Button("Register");

		roleGroup.getToggles().addAll(buyerBtn, sellerBtn);

		this.getChildren().addAll(title, usernameLb, usernameTf, passLb, passTf, phoneLb, phoneTf, addressLb, addressTf,
				roleLb, buyerBtn, sellerBtn, registerBtn);

		this.setPadding(new Insets(20));
		this.setSpacing(5);
	}
	
	private void addEvent() {
		
	}
	
}
