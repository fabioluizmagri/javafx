import javax.swing.JOptionPane;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class LoginApp extends Application {
	
	AnchorPane pane;
	TextField txtLogin;
	PasswordField txtPassword;
	Button btnEntrar, btnSair;
	private static Stage stage;
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponets();
		initListeners();
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		
		//Remove a opção maximizar da tela
		stage.setResizable(false);
		
		//Dá um título para a tela
		stage.setTitle("Login - GolFX");
		
		stage.show();
		initLayout();
		
		LoginApp.stage = stage;
	}
	
	private void initComponets () {
		pane = new AnchorPane();
		pane.setPrefSize(400, 300);
		pane.setStyle("-fx-background-color: linear-gradient(from 0% 0% to 100% 100%, blue 0%, silver 100%);");
				
		txtLogin = new TextField();
		txtLogin.setPromptText("fabio");
		
		txtPassword = new PasswordField();
		txtPassword.setPromptText("senha");
		
		btnEntrar = new Button("Entrar");
		
		btnSair = new Button("Sair");
		
		pane.getChildren().addAll(txtLogin, txtPassword, btnEntrar, btnSair);
	}
	
	private void initLayout() {
		txtLogin.setLayoutX((pane.getWidth() - txtLogin.getWidth()) / 2);
		txtLogin.setLayoutY(50);
		
		txtPassword.setLayoutX((pane.getWidth() - txtPassword.getWidth()) / 2);
		txtPassword.setLayoutY(100);
		
		btnEntrar.setLayoutX((pane.getWidth() - btnEntrar.getWidth()) / 2);
		btnEntrar.setLayoutY(150);
		
		btnSair.setLayoutX((pane.getWidth() - btnSair.getWidth()) / 2);
		btnSair.setLayoutY(200);		
	}
	
	private void initListeners() {
		btnSair.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				fecharAplicacao();
			}
		});
		
		btnEntrar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle (ActionEvent event) {
				logar();
			}
		});
	}
	
	private void logar() {
		if (txtLogin.getText().equals("admin") &&
			txtPassword.getText().equals("admin")) {
			
			try {
				new VitrineApp().start(new Stage());
				LoginApp.getStage().close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else {
			JOptionPane.showMessageDialog(null, "Login ou senha inválido", "Erro", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void fecharAplicacao() {
		System.exit(0);
	}
	
	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	public TextField getTxtLogin() {
		return txtLogin;
	}

	public void setTxtLogin(TextField txtLogin) {
		this.txtLogin = txtLogin;
	}

	public PasswordField getTxtPassword() {
		return txtPassword;
	}

	public void setTxtPassword(PasswordField txtPassword) {
		this.txtPassword = txtPassword;
	}

	public Button getBtnEntrar() {
		return btnEntrar;
	}

	public void setBtnEntrar(Button btnEntrar) {
		this.btnEntrar = btnEntrar;
	}

	public Button getBtnSair() {
		return btnSair;
	}

	public void setBtnSair(Button btnSair) {
		this.btnSair = btnSair;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		LoginApp.stage = stage;
	}

	public static void main(String[] args) {
		launch(args);
		
	}

}
