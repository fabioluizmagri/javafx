import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class Carrinho {
	private static List<Produto> produtos = new ArrayList<Produto>();
	
	public void removeProduto(Produto p) {
		System.out.println("removeProduto p");
		Iterator<Produto> itProduto = produtos.iterator();
		while (itProduto.hasNext()) {
			Produto produto = itProduto.next();
			if (produto.getProduto().equals(p.getProduto()) && produto.getPreco() == p.getPreco())
				itProduto.remove();
			System.out.println(produto.getProduto());
		}
	}
	
	public void addProdutos(Produto produto) {
		//for (Produto p : produtos)
			produtos.add(produto);
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}
	
	
	
}
