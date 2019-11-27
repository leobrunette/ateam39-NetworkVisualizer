package application;

import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Model {
	private Scene scene;
	private int centralUserIndex;
	private ObservableList<String> friends;
	private int windowWidth;
	private int windowHeight;
	
	public Model(Scene scene, int centralUserIndex, ObservableList<String> friends, int windowWidth, int windowHeight) {
		this.scene = scene;
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
	public Scene getScene() {
		return scene;
	}
	public void setScene(Scene scene) {
		this.scene = scene;
	}
	public String getCentralUser() {
		return friends.get(centralUserIndex);
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
