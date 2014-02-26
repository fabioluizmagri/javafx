import java.nio.channels.SeekableByteChannel;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import com.sun.glass.ui.Platform;

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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;


public class CarrinhoApp extends Application{

	private static Stage stage;
	private AnchorPane pane;
	private TableView<ItensProperty> tbwCarrinho;
	private TableColumn<ItensProperty, String> columnProduto;
	private TableColumn<ItensProperty, Double> columnPreco;
	private Button btnExcluirItem, btnVoltarVitrine, btnConfirmaCompra;
	private static ObservableList<ItensProperty> listItens = FXCollections.observableArrayList();
	
	@Override
	public void start(Stage stage) throws Exception {
		initComponents();
		initItens();		
		initListeners();		
		
		Scene scene = new Scene(pane);
		stage.setScene(scene);
		
		stage.show();
		initLayout();
		CarrinhoApp.stage = stage;
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	private void initComponents() {
		pane = new AnchorPane();
		pane.setPrefSize(800, 600);
		
		tbwCarrinho = new TableView<ItensProperty>();
		tbwCarrinho.setPrefSize(780, 550);
		tbwCarrinho.setItems(listItens);
		
		columnProduto = new TableColumn<ItensProperty, String>();
		columnPreco = new TableColumn<ItensProperty, Double>();
		
		tbwCarrinho.getColumns().addAll(columnProduto, columnPreco);

		columnProduto.setCellValueFactory(new PropertyValueFactory<ItensProperty, String>("produto"));
		columnPreco.setCellValueFactory(new PropertyValueFactory<ItensProperty, Double>("preco"));
		
		btnExcluirItem = new Button("Excluir item");
		btnVoltarVitrine = new Button("Voltar p/ vitrine");
		btnConfirmaCompra = new Button("Confirmar Compra");
		
		pane.getChildren().addAll(tbwCarrinho, btnVoltarVitrine, btnExcluirItem, btnConfirmaCompra);
	}
	
	private void initItens() {
		for (Produto produto : VitrineApp.getCarrinho().getProdutos()) { 			
			listItens.add(new ItensProperty(produto.getProduto(), produto.getPreco()));
		}
	}	
	
	private void initLayout() {
		btnVoltarVitrine.setLayoutX(0);
		btnExcluirItem.setLayoutX(150);
		btnConfirmaCompra.setLayoutX(300);
		
		tbwCarrinho.setLayoutY((pane.getHeight() - tbwCarrinho.getHeight()) / 2);
	}
	
	private void initListeners() {
		btnExcluirItem.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				VitrineApp.getCarrinho().removeProduto(
						new Produto(tbwCarrinho.getSelectionModel().getSelectedItem().getProduto(),
									tbwCarrinho.getSelectionModel().getSelectedItem().getPreco()));
				tbwCarrinho.getItems().remove(tbwCarrinho.getSelectionModel().getSelectedItem());
			}
		});
		
		btnVoltarVitrine.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				CarrinhoApp.getStage().close();
				ItemApp.getStage().close();
				
			}
		});
		
		btnConfirmaCompra.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				Thread thread = new Thread() {
					public void run() {
							try {
								sleep(5000);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							JOptionPane.showMessageDialog(null, "Compra realizada com sucesso");
							
							new Runnable() {
								@Override
								public void run() {
									// TODO Auto-generated method stub
									CarrinhoApp.getStage().close();
									ItemApp.getStage().close();
								}
								
							};
					}
				};
				
				thread.start();
			}
		});
		
//		tbwVitrine.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<ItensProperty>() {
//
//			@Override
//			public void changed(ObservableValue<? extends ItensProperty> value,
//					ItensProperty oldItem, ItensProperty newItem) {
//				ItemApp.setProduto(new Produto(newItem.getProduto(), newItem.getPreco()));
//				ItemApp.setIndex(tbwVitrine.getSelectionModel().getSelectedIndex());
//				
//				try {
//					new ItemApp().start(new Stage());
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//				
//			}
//			
//		});
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

	public static Stage getStage() {
		return stage;
	}
	
	
	
//	private ObservableList<ItensProperty> findItens() {
//		ObservableList<ItensProperty> itensEncontrados = FXCollections.observableArrayList();
//		for (ItensProperty itens : listItens) {
//			if (itens.getProduto().contains(txtPesquisa.getText()))
//				itensEncontrados.add(itens);
//		}
//		
//		return itensEncontrados;
//	}
}
