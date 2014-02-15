import java.util.ArrayList;
import java.util.List;


public class Vitrine {
	private static List<Produto> produtos = new ArrayList<Produto>();
	
	public void addProdutos(List<Produto> ps) {
		for (Produto p : ps)
			produtos.add(p);
	}

	public static List<Produto> getProdutos() {
		return produtos;
	}
	
	
	
}
