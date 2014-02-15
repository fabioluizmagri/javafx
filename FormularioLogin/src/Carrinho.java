import java.util.ArrayList;
import java.util.List;


public class Carrinho {
	private static List<Produto> produtos = new ArrayList<Produto>();
	
	public void addProdutos(Produto produto) {
		for (Produto p : produtos)
			produtos.add(p);
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}
	
	
	
}
