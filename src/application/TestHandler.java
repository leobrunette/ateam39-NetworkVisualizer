package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;

public class TestHandler implements EventHandler<ActionEvent>{
	Node node;
	public TestHandler(Node n) {
		node = n;
	}
	public void handle(ActionEvent e) {
		e.getSource();
		
	}

}
