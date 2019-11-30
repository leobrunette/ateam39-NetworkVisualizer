package application;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Model {
	private Stage stage;
	private String centralUser;
	private ObservableList<String> friends;
	private int windowWidth;
	private int windowHeight;
	
	public Model(Stage stage, String centralUser, ObservableList<String> friends, int windowWidth, int windowHeight) {
		this.stage = stage;
		this.centralUser = centralUser;
		this.friends = friends;
		this.windowWidth = windowWidth;
		this.windowHeight = windowHeight;
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
	public ObservableList<String> getFriends() {
		return friends;
	}
	public void setFriends(ObservableList<String> friends) {
		this.friends = friends;
	}
	
}
