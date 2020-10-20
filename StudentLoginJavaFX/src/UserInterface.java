import java.util.ArrayList;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application {

	// JavaFX stuff
	TextField nameText;
	Label nameLabel;
	TextField emailText;
	Label emailLabel;
	PasswordField passwordField;
	Label passwordLabel;
	Text errorMessage;
	Text displayName;
	Text displayEmail;
	Text displayId;
	VBox signUp;
	VBox login;
	Button submitBtn;
	Scene signUpScene;
	Scene loginScene;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ImageView logoView = new ImageView(new Image("vuw-logo.png"));
		logoView.setPreserveRatio(true);
		logoView.setFitWidth(300);
		
		emailText = new TextField();
		emailLabel = new Label("Email address:");
		emailLabel.setLabelFor(emailText);
		nameText = new TextField();
		nameLabel = new Label("Full name:");
		nameLabel.setLabelFor(nameText);
		passwordField = new PasswordField();
		passwordLabel = new Label("Password:");
		passwordLabel.setLabelFor(passwordField);
		errorMessage = new Text();
		displayName = new Text();
		
		
		submitBtn = new Button();
		submitBtn.setText("Sign Up");
		
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String name = nameText.getText();
				String email = emailText.getText();
				String password = passwordField.getText();
				
				// need to check for uniqueness of email address
				if (Student.emailExists(email)) {
					
					// print error message
					errorMessage.setText("Email already exists in system.");
					System.out.println("Email already exists");
					
					return;
				}
				errorMessage.setText("");
				
				Student student = new Student(name, email, password);
				
				System.out.println(student.toString());

				// create the scene in here, then run stage.setscene
				loginScene = createWelcomeScreen(student);
				
				primaryStage.setScene(loginScene);
			}
			
		});
		
		login = new VBox();
//		login.getChildren().add();
		
		
		signUp = new VBox();
		signUp.getChildren().add(nameLabel);
		signUp.getChildren().add(nameText);
		signUp.getChildren().add(emailLabel);
		signUp.getChildren().add(emailText);
		signUp.getChildren().add(passwordLabel);
		signUp.getChildren().add(passwordField);
		signUp.getChildren().add(submitBtn);
		signUp.getChildren().add(errorMessage);
		signUp.getChildren().add(logoView);
		
//		loginScene = new Scene(login, 500, 350);
		signUpScene = new Scene(signUp, 500, 350);
		
//		Scene login = new Scene(login, 500, 350);
		
		primaryStage.setTitle("User Sign Up");
		primaryStage.setScene(signUpScene);
		primaryStage.show();
		
		// pass the stage around the scenes. call stage.setScene
	}
	
	private Scene createWelcomeScreen(Student student) {
		// grab student name and email
		String name = student.getName();
		String email = student.getEmail();
		int id = student.getUniqueId();
		
		// add to the relevant texts,
		displayName = new Text("Hi, " + name + "!");
		displayId = new Text("ID: " + Integer.toString(id));
		displayEmail = new Text(email);
		
		// add texts to the login pane
		login.getChildren().add(displayName);
		login.getChildren().add(displayId);
		login.getChildren().add(displayEmail);
		
		// add to login scene and return scene???
		loginScene = new Scene(login, 500, 350);
		return loginScene;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
