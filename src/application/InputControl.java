package application;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputControl extends HBox{
	Label label;
	TextField textField;
	Button button;
	public InputControl(String l, String tf, String b, String prompt) {
		if(l!=null) {
			label = new Label(l);
			this.getChildren().add(label);
		}
		if(tf!=null) {
			textField = new TextField(tf);
			textField.setPromptText(prompt);
			this.getChildren().add(textField);
		}
		if(b!=null) {
			button = new Button(b);
			this.getChildren().add(button);
		}
	}
}
