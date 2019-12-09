package application;

import java.util.ArrayList;
import java.util.List;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class VisualizerPane extends StackPane {
	private Button centralUser;
	private List<String> friendsList;
	private ArrayList<Button> friends;
	private ArrayList<Line> connections;
	private Label noCentral;
	private Pane buttons;
	private Pane lines;
	private Label status;
	private Pane statusPane;

	public VisualizerPane(Controller controller) {
		buttons = new Pane();
		lines = new Pane();
		statusPane = new Pane();
		status = new Label("status: "+controller.getStatus());
		status.setTranslateX(10);
		status.setTranslateY(10);
		statusPane.getChildren().add(status);
		if (controller.getCentralUser() != null && !controller.isNetworkEmpty()) {
			centralUser = new Button(controller.getCentralUser());
			centralUser.setTranslateX((controller.getWindowWidth() / 2) - 30);
			centralUser.setTranslateY((1 * (controller.getWindowHeight() / 9)) - 30);
			friends = new ArrayList<Button>();
			connections = new ArrayList<Line>();
			friendsList = controller.getFriendsOfUser(controller.getCentralUser());
			for (int f = 0; f < friendsList.size(); f++) {
				Button button = new Button(friendsList.get(f));
				button.getStyleClass().addAll("vertex", "normal");
				button.setTranslateY((4 * (controller.getWindowHeight() / 9)) - 30);
				button.setTranslateX(((f + 1) * (controller.getWindowWidth() / (friendsList.size() + 1))) - (30));
				button.translateZProperty().set(-10);
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
					public void handle(ActionEvent e) {
						controller.changeCentralUser(button.getText());
					}
				});
				this.friends.add(button);
				makeLine(button, centralUser);
			}
			collapseContainers();
			setAttributes();
		} else if (!controller.isNetworkEmpty()) {
			noCentral = new Label("no central user selected");
			noCentral.getStyleClass().add("noCentralUser");
			noCentral.setTranslateY((2.5 * (controller.getWindowHeight() / 9)) - 30);
			this.getChildren().add(noCentral);
		} else {
			noCentral = new Label("no users in network");
			noCentral.getStyleClass().add("noCentralUser");
			noCentral.setTranslateY((2.5 * (controller.getWindowHeight() / 9)) - 30);
			this.getChildren().add(noCentral);
		}
	}

	private void setAttributes() {
		centralUser.setDisable(true);
		centralUser.getStyleClass().addAll("vertex", "primary");
		status.getStyleClass().add("status");
	}

	private void collapseContainers() {
		buttons.getChildren().add(centralUser);
		buttons.getChildren().addAll(friends);
		lines.getChildren().addAll(connections);
		this.getChildren().addAll(statusPane, lines, buttons);
	}

	private void makeLine(Button b1, Button b2) {
		Line con = new Line(b1.getTranslateX() + (30), b1.getTranslateY() + (30), b2.getTranslateX() + (30),
				b2.getTranslateY() + (30));
		double length = Math
				.sqrt(Math.pow(con.getEndY() - con.getStartY(), 2) + Math.pow(con.getEndX() - con.getStartX(), 2));
		double angle = Math.acos((con.getEndX() - con.getStartX()) / length);
		Line output = new Line(con.getStartX() + (Math.cos(angle) * 30), con.getStartY() - (Math.sin(angle) * 30),
				con.getEndX() - (Math.cos(angle) * 30), con.getEndY() + (Math.sin(angle) * 30));
		// Add Line style here
		output.getStyleClass().add("line");
		connections.add(output);
	}
}