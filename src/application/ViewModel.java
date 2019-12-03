package application;

import java.util.List;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class ViewModel {
	private Stage stage;
	private String centralUser;
	private List<String> friends;
	private int windowWidth;
	private int windowHeight;
	private Controller controller;

	public ViewModel(Stage stage, String centralUser, List<String> friends, int windowWidth, int windowHeight,
			Controller controller) {
		this.stage = stage;
		this.centralUser = centralUser;
		this.friends = friends;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
		this.controller = controller;
	}

	public Controller getController() {
		return controller;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public Stage getStage() {
		return stage;
	}

	public void setStage(Stage stage) {
		this.stage = stage;
	}

	public String getCentralUser() {
		return centralUser;
	}

	public void setCentralUser(String centralUser) {
		this.centralUser = centralUser;
	}

	public List<String> getFriends() {
		return friends;
	}

	public void setFriends(ObservableList<String> friends) {
		this.friends = friends;
	}

}
