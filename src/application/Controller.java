package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
	private SocialNetwork network;
	private Stage stage;
	private double windowWidth;
	private double windowHeight;

	public Controller(Stage stage) {
		this.stage = stage;
		windowWidth = stage.getWidth();
		windowHeight = stage.getHeight();
		network = new SocialNetwork();
	}

	public void generateStage() {
		// Main layout
		BorderPane root = new BorderPane();
		// Set scene
		Scene scene = new Scene(root, windowWidth, windowHeight);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Set primary stage
		stage.setScene(scene);
		stage.show();
		// VisualizerPane
		VisualizerPane vis = new VisualizerPane(this);
		// ControlPane
		ControlPane control = new ControlPane(this);

		// Add primary boxes to root
		root.setTop(vis);
		root.setBottom(control);
	}

	public void changeCentralUser(String user) {
		network.setCentralUser(user);
		network.addUser(user);
		generateStage();
	}

	public void addFriend(String user) {
		network.addFriends(network.getCentralUser(), user);
		generateStage();
	}

	public void removeFriend(String user) {
		network.removeFriends(network.getCentralUser(), user);
		generateStage();
	}

	public void importNetwork(String filepath) {
		network.loadFromNetwork(new File(filepath));
		generateStage();
	}

	public void exportNetwork(String filepath) {
		network.saveToFile(new File(filepath));
		generateStage();
	}

	public void clearNetwork() {
		network = new SocialNetwork();
		generateStage();
	}

	public List<String> getFriendsOfUser(String user) {
		return network.getFriends(user);
	}

	public boolean isNetworkEmpty() {
		return network.getNumberOfUsers() == 0;
	}

	public double getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(double windowWidth) {
		this.windowWidth = windowWidth;
	}

	public double getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(double windowHeight) {
		this.windowHeight = windowHeight;
	}

	public String getCentralUser() {
		return network.getCentralUser();
	}
}
