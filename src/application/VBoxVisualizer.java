package application;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Bounds;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;

public class VBoxVisualizer {
	private VBox primary;
	private Button centralUser;
	private ArrayList<Button> friends;
	private ArrayList<Line> connections;
	private HBox hboxFriends;
	private Label noCentral;
	private StackPane stack;

	public VBoxVisualizer(String cUser, List<String> friends) {
		primary = new VBox();
		if (cUser != null) {
			centralUser = new Button(cUser);
			this.friends = new ArrayList<Button>();
			connections = new ArrayList<Line>();
			hboxFriends = new HBox();
			stack = new StackPane();
			for (String f : friends) {
				Button button = new Button(f);
				this.friends.add(button);
				Bounds n1InCommonAncestor = getRelativeBounds(centralUser, primary);
				Bounds n2InCommonAncestor = getRelativeBounds(button, primary);
				Point2D n1Center = getCenter(n1InCommonAncestor);
				Point2D n2Center = getCenter(n2InCommonAncestor);

				Line connector = new Line(n1Center.getX(), n1Center.getY(), n2Center.getX(), n2Center.getY());
				connections.add(connector);
			}
			setAttributes();
			collapseContainers();
		}else {
			noCentral = new Label("no central user selected");
			primary.getChildren().add(noCentral);
		}
	}

	private void setAttributes() {
		primary.getStyleClass().add("visualizer");
		hboxFriends.getStyleClass().add("visualizerHBox");
		centralUser.setDisable(true);
		centralUser.getStyleClass().add("vertex");
		for(Button button : friends) {
			button.getStyleClass().add("vertex");
		}
		for(Line line : connections) {
			
		}
	}

	private void collapseContainers() {
		hboxFriends.getChildren().addAll(friends);
		primary.getChildren().addAll(centralUser,hboxFriends);
	}

	public VBox getVBoxVisualizer() {
		return primary;
	}

	private Bounds getRelativeBounds(Node node, Node relativeTo) {
	    Bounds nodeBoundsInScene = node.localToScene(node.getBoundsInLocal());
	    return relativeTo.sceneToLocal(nodeBoundsInScene);
	}

	private Point2D getCenter(Bounds b) {
	    return new Point2D(b.getMinX() + b.getWidth() / 2, b.getMinY() + b.getHeight() / 2);
	}
}
