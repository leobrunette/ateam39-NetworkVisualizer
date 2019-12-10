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

/**
 * Controller.java
 * Contains methods accessed by buttons to be passed between classes
 *
 * @author ateam39
 */

public class Controller {
	private SocialNetwork network;
	private Stage stage;
	private double windowWidth;
	private double windowHeight;
	private String status;

	public Controller(Stage stage) {
		this.stage = stage;
		windowWidth = stage.getWidth();
		windowHeight = stage.getHeight();
		network = new SocialNetwork();
		status = "";
	}

	public void generateStage() { //This method is called everytime to update the view
		// Main layout
		BorderPane root = new BorderPane();
		// Set scene
		Scene scene = new Scene(root, windowWidth, windowHeight);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Set primary stage
		stage.setScene(scene);
		stage.show();
		stage.setOnCloseRequest(closeHandler);
		// VisualizerPane includes the nodes and lines in the top half of screen
		VisualizerPane vis = new VisualizerPane(this);
		// ControlPane includes all the controls
		ControlPane control = new ControlPane(this);
		// Add primary boxes to root
		root.setTop(vis);
		root.setBottom(control);
	}

	public void changeCentralUser(String user) { //changes central user and tries to add user (which will return false if already in graph)
		network.setCentralUser(user);
		if (network.addUser(user)) {
			setStatus("added " + user + " and set as central user");
		} else {
			setStatus(user + " set as central user");
		}
		generateStage();
	}

	public void addFriend(String user) {//adds a friendship between the central user and user passed
		if(network.addFriends(network.getCentralUser(), user)) {
			setStatus("added friendship between "+network.getCentralUser()+" and "+user);
		}else {
			setStatus("failed to add friendship between "+network.getCentralUser()+" and "+user);
		}
		generateStage();
	}

	public void removeFriend(String user) {//removes friendship between central user and user passed
		if(network.removeFriends(network.getCentralUser(), user)) {
			setStatus("removed friendship between "+network.getCentralUser()+" and "+user);
		}else {
			setStatus("failed to remove friendship between "+network.getCentralUser()+" and "+user);
		}
		generateStage();
	}

	public void importNetwork(String filepath) {//imports network from the filepath provided after clearing the network
		try {
			Scanner in = new Scanner(new File(filepath));
			clearNetwork();
		} catch (Exception e) {
			
		}
		network.loadFromNetwork(new File(filepath));
		if(!network.equals(new SocialNetwork())) {
			setStatus("imported network from "+filepath);
		}else {
			setStatus("failed to import network from "+filepath);
		}
		generateStage();
	}

	public void exportNetwork(String filepath) {//exports the network to the given filepath
		network.saveToFile(new File(filepath));
		setStatus("exported network to file "+filepath);
		generateStage();
	}

	public void clearNetwork() {//clears the network by creating a new instance
		network = new SocialNetwork();
		setStatus("cleared network");
		generateStage();
	}

	public List<String> getFriendsOfUser(String user) {//returns friends of a specified user
		return network.getFriends(user);
	}

	public boolean isNetworkEmpty() {//checks if the network has no users
		return network.getNumberOfUsers() == 0;
	}

	public double getWindowWidth() {//get for the window width after maximized
		return windowWidth;
	}

	public void setWindowWidth(double windowWidth) { //set for the window width after maximized
		this.windowWidth = windowWidth;
	}

	public double getWindowHeight() { //get for the window height after maximized
		return windowHeight;
	}

	public void setWindowHeight(double windowHeight) { //set for the window height after maximized
		this.windowHeight = windowHeight;
	}

	public String getCentralUser() {//get for central user
		return network.getCentralUser();
	}

	public void setStatus(String status) {//sets the status called by methods such as addUser or addFriend
		this.status = status;
	}

	public String getStatus() {//get for status
		return status;
	}
	public void addUser(String user) { //adds single user, not friendship to network
		if(network.addUser(user)) {
			setStatus("added user "+user);
		}else {
			setStatus("failed to add user "+user);
		}
		generateStage();
	}
	private EventHandler<WindowEvent> closeHandler = event -> { //this is an event handler to be called when the window is exited
		Alert alert = new Alert(AlertType.CONFIRMATION);        //this gives the user the option to save the network on exit
		alert.setTitle("Exit Confirmation");					//first alert is one that gives option to save or exit
		alert.setHeaderText("Confirm Exit");
		alert.setContentText("Are you sure you want to exit without saving network?");

		ButtonType save = new ButtonType("Save");
		ButtonType exit = new ButtonType("Exit");
		ButtonType cancel = new ButtonType("Cancel", ButtonData.CANCEL_CLOSE);

		alert.getButtonTypes().setAll(save, exit, cancel);

		Optional<ButtonType> result = alert.showAndWait();
		if (result.get() == save) {
			TextInputDialog dialog = new TextInputDialog("");  //second alert gives the opportunity to provide file path to save to
			dialog.setTitle("Network Export");
			dialog.setHeaderText("Save to File");
			dialog.setContentText("Enter file path:");
			Optional<String> saveResult = dialog.showAndWait();
			if (saveResult.isPresent()) {
				network.saveToFile(new File(saveResult.get()));
			}
		} else if (result.get() == exit) {

		} else if (result.get() == cancel) {
			event.consume();
		}
	};
}
