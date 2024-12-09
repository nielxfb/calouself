package util;

import javafx.stage.Stage;

public class StageManager {

	private static StageManager instance;
	private Stage st;

	private StageManager(Stage st) {
		this.st = st;
	}

	public static StageManager getInstance(Stage st) {
		if (instance == null) {
			instance = new StageManager(st);
		}
		return instance;
	}

	public Stage getStage() {
		return st;
	}

}
