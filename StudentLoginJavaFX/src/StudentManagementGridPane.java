package application;

import java.io.FileInputStream;


import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class gridPane extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			Text text = new Text ("Student Log");
			Text text1 = new Text("Enter Username");
			Text text2 = new Text("Enter Password");
			TextField input1 = new TextField();
			TextField input2 = new TextField();
			Button addBtn = new Button("LOG IN");
			Button clearBtn = new Button("Clear");
			Text result = new Text();
			

			// Gridpane is a class
			GridPane gridpane = new GridPane();
			gridpane.setMinSize(400, 200);
			gridpane.setPadding(new Insets(10, 10, 10, 10));
			// padding is the space between margin and grid

			gridpane.setVgap(5); // Gap between object like button and textfield
			gridpane.setHgap(5);
			gridpane.setAlignment(Pos.TOP_LEFT); // make everything in center

			//gridpane.add(text, 0, 0);
			gridpane.add(text1, 1, 1);
			gridpane.add(input1, 2, 1);
			gridpane.add(text2, 1, 2);
			gridpane.add(input2, 2, 2);
			gridpane.add(addBtn, 2, 3);
			ImageView i = getImage();
			gridpane.add(i, 0, 0);
			// AN action listner or binding a action
			// add to numbers
			addBtn.setOnAction(action -> {
				String n1 = input1.getText();
				String n2 = input2.getText();

				// convert String to number
				int a = Integer.parseInt(n1);
				int b = Integer.parseInt(n2);
				int sum = add(a, b);

				// convert answer integer to String
				String ans = Integer.toString(sum);
				// we display the result
				result.setText(ans);

			});

			clearBtn.setOnAction(action -> {
				input1.setText(" ");
				input2.setText(" ");
				result.setText(" ");
			});

			Scene scene = new Scene(gridpane, 400, 400);
			primaryStage.setScene(scene);
			primaryStage.show();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public ImageView getImage() {
		try {
			// read file, input trseam read file from hard disk
			FileInputStream input = new FileInputStream("C:\\Users\\liewshan\\eclipse-workspace\\Calculator\\src\\application\\logo.png");
			// build image from file
			Image image = new Image(input);
			// display image
			ImageView imageView = new ImageView(image);
			// set location
//			imageView.setX(3);
//			imageView.setY(3);
			
			// this can also be set in the bug bojects and called with object.get size
			imageView.setFitHeight(40);
			imageView.setFitWidth(100);// its better to use constants here
			return imageView;

		} catch (Exception e) {
			e.printStackTrace();

		}
		return null;
	}

	
	

	public int add(int p1, int p2) {
		return p1 + p2;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
