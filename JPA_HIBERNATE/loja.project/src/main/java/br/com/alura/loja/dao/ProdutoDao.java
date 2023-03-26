package br.com.alura.loja.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.alura.loja.modelo.Produto;

public class ProdutoDao {

	private EntityManager em;

	public ProdutoDao(EntityManager em) {
		super();
		this.em = em;

	}

	public void cadastrar(Produto produto) {
		this.em.persist(produto);
	}

	public Produto buscarPorId(Long id) {
		return em.find(Produto.class, id);
	}

	public List<Produto> buscarTodos() {
		String jpql = "SELECT p FROM Produto p";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	public List<Produto> buscarPorNome(String name) {
		String jpql = "SELECT p FROM Produto p WHERE p.name = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", name).getResultList();
	}

	public List<Produto> buscarPorNomeDaCategoria(String name) {
		String jpql = "SELECT p FROM Produto p WHERE p.categoria.nome = :nome";
		return em.createQuery(jpql, Produto.class).setParameter("nome", name).getResultList();
	}
	public BigDecimal buscarPorPrecoDoProdutoComNome(String preco) {
		String jpql = "SELECT p.preco FROM Produto p WHERE p.name = :nome";
		return em.createQuery(jpql, BigDecimal.class).setParameter("nome", preco).getSingleResult();
	}
}
