package main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import util.StageManager;
import view.RegisterPage;

public class Main extends Application {

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		StageManager st = StageManager.getInstance(primaryStage);
		st.getStage().setScene(new Scene(new RegisterPage(), 800, 600));
		st.getStage().show();
	}

}
