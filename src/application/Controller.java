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
	private Graph network;
	private ArrayList<String> commands;

	public Controller() {
		network = new Graph();
		commands = new ArrayList<String>();
		setTempNetwork();
	}

	public void generateStage(ViewModel model) {
		Stage primaryStage = model.getStage();
		// Main layout
		BorderPane root = new BorderPane();
		// Set scene
		Scene scene = new Scene(root, primaryStage.getWidth(), primaryStage.getHeight());
		scene.setFill(Color.BLUE);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		// Set primary stage
		primaryStage.setScene(scene);
		// primaryStage.setMaximized(true);
		primaryStage.show();
		model = new ViewModel(primaryStage, model.getCentralUser(), getFriendsOfUser(model.getCentralUser()),
				(int) primaryStage.getWidth(), (int) primaryStage.getHeight(), this); // Set second parameter to null
																						// for no central user selected
		// VisualizerPane
		VisualizerPane vis = new VisualizerPane(model);
		// ControlPane
		ControlPane control = new ControlPane(model);

		// Add primary boxes to root
		root.setTop(vis);
		root.setBottom(control);
	}

	public void changeCentralUserFromButton(ViewModel model, String name) {
		model.setCentralUser(name);
		generateStage(model);
	}

	public void changeCentralUserFromTextField(ViewModel model, String name) {
		model.setCentralUser(name);
		network.addVertex(name);
		commands.add("a " + name);
		generateStage(model);
	}

	public void addFriend(ViewModel model, String name) {
		network.addEdge(model.getCentralUser(), name);
		commands.add("a " + model.getCentralUser() + " " + name);
		generateStage(model);
	}

	public void removeFriend(ViewModel model, String name) {
		network.removeEdge(model.getCentralUser(), name);
		generateStage(model);
	}

	public void importNetwork(ViewModel model, String filepath) {
		File file = new File(filepath);
		Scanner in = null;
		try {
			in = new Scanner(file);
		} catch (FileNotFoundException e) {

		}
		while (in.hasNextLine()) {
			String line = in.nextLine();
			String[] partsOfLine = line.split(" ");
			if (partsOfLine[0].equals("a")) {
				if (partsOfLine.length == 2) {
					network.addVertex(partsOfLine[1]);
				} else {
					network.addEdge(partsOfLine[1], partsOfLine[2]);
				}
			} else if (partsOfLine[0].equals("r")) {
				if (partsOfLine.length == 2) {
					network.removeVertex(partsOfLine[1]);
				} else {
					network.removeEdge(partsOfLine[1], partsOfLine[2]);
				}
			} else if (partsOfLine[0].equals("s")) {
				model.setCentralUser(partsOfLine[1]);
			}
		}
		generateStage(model);
	}

	public void exportNetwork() {

	}

	public void clearNetwork(ViewModel model) {
		network = new Graph();
		model.setCentralUser(null);
		generateStage(model);
	}

	public List<String> getFriendsOfUser(String user) {
		return network.getAdjacentVerticesOf(user);
	}

	public boolean isNetworkEmpty() {
		return network.order() == 0;
	}

	public void setTempNetwork() {
		network.addVertex("user0");
		network.addVertex("user1");
		network.addVertex("user2");
		network.addVertex("user3");
		network.addVertex("user4");
		network.addVertex("user5");
		network.addVertex("user6");
		network.addVertex("user7");
		network.addEdge("user0", "user1");
		network.addEdge("user0", "user2");
		network.addEdge("user1", "user3");
		network.addEdge("user2", "user3");
		network.addEdge("user3", "user4");
		network.addEdge("user3", "user5");
		network.addEdge("user3", "user6");
		network.addEdge("user3", "user7");
	}
}
