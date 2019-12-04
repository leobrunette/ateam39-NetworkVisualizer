package application;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputControl extends HBox{
	Label label;
	TextField textField;
	Button button;
	public InputControl(String l, String tf, String b, String prompt, boolean disable, ViewModel model) {
		if(l!=null) {
			label = new Label(l);
			label.getStyleClass().add("labelInput");
			label.setAlignment(Pos.CENTER_RIGHT);
			label.setDisable(disable);
			this.getChildren().add(label);
		}
		if(tf!=null) {
			textField = new TextField(tf);
			textField.setPromptText(prompt);
			textField.getStyleClass().add("textInput");
			textField.setDisable(disable);
			this.getChildren().add(textField);
		}
		if(b!=null) {
			button = new Button(b);
			button.getStyleClass().add("butInput");
			if(b.equals("clear")||b.equals("remove")) {
				button.getStyleClass().add("red");
			}
			button.setDisable(disable);
			switch(b) {
			case "clear":
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
        			public void handle(ActionEvent e) {
        				model.getController().clearNetwork(model);
        			}
				});
				break;
			case "set":
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
        			public void handle(ActionEvent e) {
        				model.getController().changeCentralUserFromTextField(model, textField.getText());
        			}
				});
				break;
			case "add user":
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
        			public void handle(ActionEvent e) {
        				model.getController().changeCentralUserFromTextField(model, textField.getText());
        			}
				});
				break;
			case "add":
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
        			public void handle(ActionEvent e) {
        				model.getController().addFriend(model, textField.getText());
        			}
				});
				break;	
			case "remove":
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
        			public void handle(ActionEvent e) {
        				model.getController().removeFriend(model, textField.getText());
        			}
				});
				break;
			case "import":
				button.setOnAction(new EventHandler<ActionEvent>() {
					@Override
        			public void handle(ActionEvent e) {
        				model.getController().importNetwork(model, textField.getText());
        			}
				});
				break;
			}
			this.getChildren().add(button);
		}
		this.getStyleClass().add("inputControl");
	}
}
