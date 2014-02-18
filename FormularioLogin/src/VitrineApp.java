import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class VitrineApp extends Application{

	private static Stage stage;
	private AnchorPane pane;
	private TextField txtPesquisa;
	private TableView<ItensProperty> tbwVitrine;
	private TableColumn<ItensProperty, String> columnProduto;
	private TableColumn<ItensProperty, Double> columnPreco;
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
	private static Carrinho carrinho;
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initListeners();
		initItens();
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		
		stage.show();
		initLayout();
		VitrineApp.stage = stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);
		
		txtPesquisa = new TextField();
		txtPesquisa.setPromptText("Digite o item de pesquisa");
		
		tbwVitrine = new TableView<ItensProperty>();
		tbwVitrine.setPrefSize(780, 550);
		tbwVitrine.setItems(listItens);
		
		columnProduto = new TableColumn<ItensProperty, String>();
		columnPreco = new TableColumn<ItensProperty, Double>();
		
		tbwVitrine.getColumns().addAll(columnProduto, columnPreco);
		
		pane.getChildren().addAll(txtPesquisa,tbwVitrine);
		
		carrinho = new Carrinho();
		
		columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));
	}
	
	private void initLayout() {
		txtPesquisa.setLayoutX((pane.getWidth() - txtPesquisa.getWidth()) / 2);
		tbwVitrine.setLayoutY((pane.getHeight() - tbwVitrine.getHeight()) / 2);
	}
	
	private void initListeners() {
		txtPesquisa.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				if (!txtPesquisa.getText().equals("")) {
					tbwVitrine.setItems(findItens());
				} else {
					tbwVitrine.setItems(listItens);
				}
				
			}
		});
		
		tbwVitrine.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItensProperty>() {

			@Override
			public void changed(ObservableValue<? extends ItensProperty> value,
					ItensProperty oldItem, ItensProperty newItem) {
				ItemApp.setProduto(new Produto(newItem.getProduto(), newItem.getPreco()));
				ItemApp.setIndex(tbwVitrine.getSelectionModel().getSelectedIndex());
				
				try {
					new ItemApp().start(new Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
				
			}
			
		});
	}
	
	private void initItens() {
		Vitrine v = new Vitrine();
		
		List<Produto> produtos = new ArrayList<Produto>();
		
		Produto bola = new Produto();
		bola.setProduto("Bola Topper");
		bola.setPreco(15);
		
		Produto luva = new Produto();
		luva.setProduto("Luvas Umbro");
		luva.setPreco(9);
		
		Produto camisa = new Produto();
		camisa.setProduto("Camisa Esportiva");
		camisa.setPreco(40);
		
		Produto chuteira = new Produto();
		chuteira.setProduto("Chuteira Nike Mercurial");
		chuteira.setPreco(199);
		
		Produto caneleira = new Produto();
		caneleira.setProduto("Caneleira Topper");
		caneleira.setPreco(10);
		
		produtos.add(bola);
		produtos.add(luva);
		produtos.add(camisa);
		produtos.add(chuteira);
		produtos.add(caneleira);
		
		v.addProdutos(produtos);
		
		for (Produto produto : v.getProdutos()) 			
			listItens.add(new ItensProperty(produto.getProduto(), produto.getPreco()));
	}
	
	public class ItensProperty {
		private SimpleStringProperty produto;
		private SimpleDoubleProperty preco;
		
		public ItensProperty(String produto, double preco) {
			this.produto = new SimpleStringProperty(produto);
			this.preco = new SimpleDoubleProperty(preco);
		}

		public String getProduto() {
			return produto.get();
		}

		public void setProduto(String produto) {
			this.produto.set(produto);
		}

		public double getPreco() {
			return preco.get();
		}

		public void setPreco(double preco) {
			this.preco.set(preco);
		}
		
	}
	
	private ObservableList<ItensProperty> findItens() {
		ObservableList<ItensProperty> itensEncontrados = FXCollections.observableArrayList();
		for (ItensProperty itens : listItens) {
			if (itens.getProduto().contains(txtPesquisa.getText()))
				itensEncontrados.add(itens);
		}
		
		return itensEncontrados;
	}
}
