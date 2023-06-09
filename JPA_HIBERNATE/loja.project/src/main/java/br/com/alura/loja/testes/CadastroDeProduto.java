package br.com.alura.loja.testes;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.dao.CategoriaDao;
import br.com.alura.loja.dao.ProdutoDao;
import br.com.alura.loja.modelo.Categoria;
import br.com.alura.loja.modelo.Produto;
import br.com.alura.loja.util.JPAUtil;

public class CadastroDeProduto {

	public static void main(String[] args) {

		cadastrarProduto();
		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtodao = new ProdutoDao(em);

		Produto p = produtodao.buscarPorId(1l);
		System.out.println(p.getPreco());
		
		List<Produto> todos = produtodao.buscarPorNomeDaCategoria("CELULARES");
		todos.forEach(p2 -> System.out.println(p.getName()));
		
		BigDecimal preco = produtodao.buscarPorPrecoDoProdutoComNome("xaomi poco x3");
		System.out.println("Preço do produto: "+preco);
	}

	private static void cadastrarProduto() {
		Categoria celulares = new Categoria("CELULARES");

		Produto celular = new Produto("xaomi poco x3", "Muito top", new BigDecimal(2500.50), celulares);

		EntityManager em = JPAUtil.getEntityManager();
		ProdutoDao produtodao = new ProdutoDao(em);
		CategoriaDao categoriaDao = new CategoriaDao(em);

		em.getTransaction().begin();
		categoriaDao.cadastrar(celulares);
		produtodao.cadastrar(celular);
		em.getTransaction().commit();
		em.close();
	}

}