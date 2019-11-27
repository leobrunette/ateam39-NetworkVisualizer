package application;

import javafx.collections.ObservableList;
import javafx.stage.Stage;

public class Model {
	private Stage stage;
	private int centralUserIndex;
	private ObservableList<String> friends;
	private int windowWidth;
	private int windowHeight;
	
	public Model(Stage stage, int centralUserIndex, ObservableList<String> friends, int windowWidth, int windowHeight) {
		this.stage = stage;
		this.centralUserIndex = centralUserIndex;
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
		return friends.get(centralUserIndex);
	}
	public int getCentralUserIndex() {
		return centralUserIndex;
	}
	public void setCentralUser(int centralUserIndex) {
		this.centralUserIndex = centralUserIndex;
	}
	public ObservableList<String> getFriends() {
		ObservableList<String> output = friends;
		output.remove(centralUserIndex);
		return output;
	}
	public void setFriends(ObservableList<String> friends) {
		this.friends = friends;
	}
	
}
