import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class ItemApp extends Application {
	private AnchorPane pane;
	private ImageView imgItem;
	private Label lblPreco, lblDescricao;
	private Button btnAddCarrinho;
	private static Stage stage;
	private static Produto produto;
	
	private static int index;
	private static String[] images = {"http://pintandoecolorindo.com.br/wp-content/uploads/2013/04/bola-em-png.png",
									  "http://mundodosgoleiros.com.br/Eshop.Admin/imagens/mundodosesportes/Thumbs/TN500_luva-branca.jpg",
									  "http://www.atrativoweb.com/wp-content/uploads/2012/01/camisa-do-Barcelona.jpg",
									  "http://www.lugoesportes.com.br/imagens/lugoesportes.com.br/produtos/Futebol/chuteiras/nike/2013/chuteira_nike_mercurial_vortex_fg_1.jpg",
									  "http://img.submarino.com.br/produtos/01/00/item/115494/0/115494055_2GG.jpg"};
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initComponets() {
		pane = new AnchorPane();
		pane.setPrefSize(600, 400);
		
		imgItem = new ImageView(new Image(images[index]));
		
		lblDescricao = new Label(produto.getProduto());
		lblPreco = new Label("Preço");		
		
		btnAddCarrinho = new Button("Adicionar p/ carrinho");
		
		pane.getChildren().addAll(lblDescricao, lblPreco, btnAddCarrinho, imgItem);
	}
	
	private void initLayout() {
		lblDescricao.setLayoutX((pane.getWidth() - lblDescricao.getWidth()) / 2);
		lblDescricao.setLayoutY(0);
		
		lblPreco.setLayoutX((pane.getWidth() - lblPreco.getWidth()) / 2);
		lblPreco.setLayoutY(20);
		
		btnAddCarrinho.setLayoutX((pane.getWidth() - btnAddCarrinho.getWidth()) / 2);
		btnAddCarrinho.setLayoutY(40);
		
		imgItem.setLayoutY(60);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponets();
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		
		stage.show();
		initLayout();
		
		ItemApp.stage = stage;
	}

	public AnchorPane getPane() {
		return pane;
	}

	public void setPane(AnchorPane pane) {
		this.pane = pane;
	}

	public ImageView getImgItem() {
		return imgItem;
	}

	public void setImgItem(ImageView imgItem) {
		this.imgItem = imgItem;
	}

	public Label getLblPreco() {
		return lblPreco;
	}

	public void setLblPreco(Label lblPreco) {
		this.lblPreco = lblPreco;
	}

	public Label getLblDescricao() {
		return lblDescricao;
	}

	public void setLblDescricao(Label lblDescricao) {
		this.lblDescricao = lblDescricao;
	}

	public Button getBtnAddCarrinho() {
		return btnAddCarrinho;
	}

	public void setBtnAddCarrinho(Button btnAddCarrinho) {
		this.btnAddCarrinho = btnAddCarrinho;
	}

	public static Stage getStage() {
		return stage;
	}

	public static void setStage(Stage stage) {
		ItemApp.stage = stage;
	}

	public static Produto getProduto() {
		return produto;
	}

	public static void setProduto(Produto produto) {
		ItemApp.produto = produto;
	}

	public static int getIndex() {
		return index;
	}

	public static void setIndex(int index) {
		ItemApp.index = index;
	}
	
	

}
	