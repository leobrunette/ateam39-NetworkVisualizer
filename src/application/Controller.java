package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

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
		stage.setOnCloseRequest(closeHandler);
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
	
	private EventHandler<WindowEvent> closeHandler = event  -> {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Exit Confirmation");
        alert.setHeaderText("Confirm Exit");
        alert.setContentText("Are you sure you want to exit without saving network?");

        ButtonType save = new ButtonType("Save");
        ButtonType exit = new ButtonType("Exit");
        ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

        alert.getButtonTypes().setAll(save, exit, cancel);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == save){
        	TextInputDialog dialog = new TextInputDialog("");
        	dialog.setTitle("Network Export");
        	dialog.setHeaderText("Save to File");
        	dialog.setContentText("Enter file path:");

        	// Traditional way to get the response value.
        	Optional<String> saveResult = dialog.showAndWait();
        	if (saveResult.isPresent()){
        	    network.saveToFile(new File(saveResult.get()));
        	}
        } else if (result.get() == exit) {
        	
        } else if (result.get() == cancel) {
            event.consume();
        }
	};
}
