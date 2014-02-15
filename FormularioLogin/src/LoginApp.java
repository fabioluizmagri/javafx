import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginApp extends Application {

	@Override
	public void start(Stage stage) throws Exception {
		AnchorPane pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		
		TextField txtLogin = new TextField();
		txtLogin.setPromptText("fabio");
		
		PasswordField txtPassword = new PasswordField();
		txtPassword.setPromptText("senha");
		
		Button btnEntrar = new Button("Entrar");
		Button btnSair = new Button("Sair");
		
		pane.getChildren().addAll(txtLogin, txtPassword, btnEntrar, btnSair);
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		stage.show();
		
		txtLogin.setLayoutX((pane.getWidth() - txtLogin.getWidth()) / 2);
		txtLogin.setLayoutY(50);
		
		txtPassword.setLayoutX((pane.getWidth() - txtPassword.getWidth()) / 2);
		txtPassword.setLayoutY(100);
		
		btnEntrar.setLayoutX((pane.getWidth() - btnEntrar.getWidth()) / 2);
		btnEntrar.setLayoutY(150);
		
		btnSair.setLayoutX((pane.getWidth() - btnSair.getWidth()) / 2);
		btnSair.setLayoutY(200);
		
		pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");
	}
	
	public static void main(String[] args) {
		launch(args);
		
	}

}
