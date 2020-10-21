import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class UserInterface extends Application {

	// JavaFX stuff
	TextField loginEmail;
	Label loginEmailLabel;
	PasswordField loginPassword;
	Label loginPasswordLabel;
	TextField nameText;
	Label nameLabel;
	TextField emailText;
	Label emailLabel;
	PasswordField passwordField;
	Label passwordLabel;
	Text errorMessage;
	Text loginErrorMessage;
	Text displayName;
	Text displayEmail;
	Text displayId;
	GridPane signUp;
	GridPane login;
	VBox loginTest;
	VBox welcome;
	Button submitBtn;
	Scene signUpScene;
	Scene loginScene;
	Scene welcomeScene;
	Button logoutBtn;
	Button existingUserBtn;
	Button signUpBtn;
	Button loginBtn;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		
		ImageView logoView = new ImageView(new Image("vuw-logo.png"));
		ImageView logoViewLogin = new ImageView(new Image("vuw-logo.png"));
		logoView.setPreserveRatio(true);
		logoViewLogin.setPreserveRatio(true);
		logoView.setFitWidth(300);
		logoViewLogin.setFitWidth(300);
		

		loginEmail = new TextField();
		loginEmailLabel = new Label("Email address:");
		loginEmailLabel.setLabelFor(loginEmail);
		loginPassword = new PasswordField();
		loginPasswordLabel = new Label("Password:");
		loginPasswordLabel.setLabelFor(loginPassword);
		
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
		loginErrorMessage = new Text();
		
		welcome = new VBox();
		loginBtn = new Button("Login");
		submitBtn = new Button("Sign Up");
		logoutBtn = new Button("Logout");
		signUpBtn = new Button("Return to Sign Up");
		existingUserBtn = new Button("Student Login");
		
		loginBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				String email = loginEmail.getText();
				String password = loginPassword.getText();
				
				// search for student using email
				if (Student.emailExists(email)) {
					// check password matches
					if (Student.checkPasswordMatches(email, password)) {
						
						// get student id
						int id = Student.getStudentIDFromEmail(email);
						
						// clear inputs
						clearInputs();
						
						// get the student and set welcome scene
						welcomeScene = createWelcomeScreen(Student.getStudent(id));
						primaryStage.setScene(welcomeScene);
						primaryStage.setTitle("Welcome");
						primaryStage.show();
						
					} else {
						loginErrorMessage.setText("Incorrect password");
					}
					
				} else {
					// if email is not found, set error message
					loginErrorMessage.setText("Could not find email");
				}

			}
		});
		
		signUpBtn.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				primaryStage.setScene(signUpScene);
				primaryStage.setTitle("Sign Up");
				primaryStage.show();
			}
		});
		
		existingUserBtn.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent arg) {
				primaryStage.setScene(loginScene);
				primaryStage.setTitle("Login");
				primaryStage.show();
			}
		});
		
		logoutBtn.setOnAction(new EventHandler<ActionEvent>() {
			
			public void handle(ActionEvent arg) {
				primaryStage.setScene(loginScene);
				primaryStage.setTitle("Login");
				primaryStage.show();
			}
		});
		
		submitBtn.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// fetch input text and check for empty inputs and present error messages
				String name = nameText.getText();
				if (name.equals("")) {
					errorMessage.setText("Must provide name.");
					return;
				}
				String email = emailText.getText();
				if (email.equals("")) {
					errorMessage.setText("Must provide email address.");
					return;
				}
				String password = passwordField.getText();
				if (password.equals("")) {
					errorMessage.setText("Must enter password.");
					return;
				}
				
				// need to check for uniqueness of email address
				if (Student.emailExists(email)) {
					
					// print error message
					errorMessage.setText("Email already exists in system.");
					
					return;
				}
				
				// clear input fields
				clearInputs();
				
				// create new student
				Student student = new Student(name, email, password);
				// create the scene in here, then run stage.setscene
				welcomeScene = createWelcomeScreen(student);
				
				
				primaryStage.setScene(welcomeScene);
				primaryStage.setTitle("Welcome");
			}
			
		});
		
		// create login pane
		login = new GridPane();
		login.setMinSize(400, 200);
		login.setPadding(new Insets(10, 10, 10, 10));
		// padding is the space between margin and grid
		
		login.setVgap(5); // Gap between object like button and textfield
		login.setHgap(5);
		login.setAlignment(Pos.TOP_LEFT); 
//		
//		//login.add(text, 0, 0);
		/* 		loginEmail = new TextField();
		loginEmailLabel = new Label("Email address:");
		loginEmailLabel.setLabelFor(loginEmail);
		loginPassword = new PasswordField();
		loginPasswordLabel = new Label("Password:");
		loginPasswordLabel.setLabelFor(loginPassword);
		*/
		
		login.add(loginEmailLabel, 1, 1);
		login.add(loginEmail, 2, 1);
		login.add(loginPasswordLabel, 1, 2);
		login.add(loginPassword, 2, 2);
		login.add(loginBtn, 2, 3);
		login.add(loginErrorMessage, 2, 4);
		login.add(logoViewLogin, 0, 0);
		login.add(signUpBtn, 1, 0);
		
		// create sign up pane
		GridPane signUp = new GridPane();
		signUp.setMinSize(400, 200);
		signUp.setPadding(new Insets(10, 10, 10, 10));
		// padding is the space between margin and grid
		
		signUp.setVgap(5); // Gap between object like button and textfield
		signUp.setHgap(5);
		signUp.setAlignment(Pos.TOP_LEFT); // make everything in center
		
		//signUp.add(text, 0, 0);
		signUp.add(nameLabel, 1, 1);
		signUp.add(nameText, 2, 1);
		signUp.add(emailLabel, 1, 2);
		signUp.add(emailText, 2, 2);
		signUp.add(passwordLabel, 1, 3);
		signUp.add(passwordField, 2, 3);
		signUp.add(submitBtn, 2, 4);
		signUp.add(errorMessage, 2, 5);
		signUp.add(logoView, 0, 0);
		signUp.add(existingUserBtn, 1, 0);

		
		loginScene = new Scene(login, 600, 350);
		signUpScene = new Scene(signUp, 600, 350);
		
		primaryStage.setTitle("User Sign Up");
		primaryStage.setScene(signUpScene);
		primaryStage.show();
		
	}
	
	private void clearInputs() {
		loginEmail.clear();
		loginPassword.clear();
		nameText.clear();
		emailText.clear();
		passwordField.clear();
		errorMessage.setText("");
		loginErrorMessage.setText("");
	}
	
	private Scene createWelcomeScreen(Student student) {
		// grab student name and email
		String name = student.getName();
		String email = student.getEmail();
		int id = student.getUniqueId();
		
		// add to the relevant texts,
		displayName = new Text("Hi, " + name + "!");
		displayId = new Text("ID: " + Integer.toString(id));
		displayEmail = new Text("Email address: " + email);
		displayName.setId("name");
		displayEmail.setId("email");
		displayId.setId("id");
		
		// clear welcome pane
		welcome = new VBox();
		welcome.setId("vbox");
		welcome.setAlignment(Pos.CENTER_LEFT);
		logoutBtn.setId("button");
		
		// add texts to the welcome pane
		welcome.getChildren().add(displayName);
		welcome.getChildren().add(displayId);
		welcome.getChildren().add(displayEmail);
		welcome.getChildren().add(logoutBtn);
		
		// add to welcome scene and return scene
		welcomeScene = new Scene(welcome, 600, 350);
		welcomeScene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
		return welcomeScene;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}
