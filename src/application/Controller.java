package application;

import java.io.File;
import java.util.List;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Controller {
	private Graph network;
	public Controller() {
		network = new Graph();
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
		//primaryStage.setMaximized(true);
		primaryStage.show();
		model = new ViewModel(primaryStage, model.getCentralUser(), getFriendsOfUser(model.getCentralUser()), (int) primaryStage.getWidth(), (int) primaryStage.getHeight(), this); // Set second parameter to null for no central user selected
		// VisualizerPane
		 VisualizerPane vis = new VisualizerPane(model);
		// ControlPane
		ControlPane control = new ControlPane(model);

		// Add primary boxes to root
		root.setTop(vis);
		root.setBottom(control);

	}

	public void changeCentralUserFromButton(ViewModel model, Button button) {
		model.setCentralUser(button.getText());
		generateStage(model);
	}
	
	public void importNetwork(File file) {
		
	}
	
	public List<String> getFriendsOfUser(String user){
		return network.getAdjacentVerticesOf(user);
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
		network.addEdge("user0","user1"); network.addEdge("user0","user2");
		network.addEdge("user1","user3"); network.addEdge("user2","user3");
		network.addEdge("user3","user4"); network.addEdge("user3","user5"); network.addEdge("user3","user6"); network.addEdge("user3","user7");
	}
}
