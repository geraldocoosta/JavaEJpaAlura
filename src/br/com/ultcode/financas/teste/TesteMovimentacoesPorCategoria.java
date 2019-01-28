package br.com.ultcode.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ultcode.financas.modelo.Categoria;
import br.com.ultcode.financas.modelo.Movimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteMovimentacoesPorCategoria {

	/*
	 * Lembrando que o relacionamento entre Movimentacao e Categoria é do
	 * tipo @ManyToMany, o que significa que ele é mapeado em outra tabela - sendo
	 * assim, de que forma utilizaremos esse relacionamento no filtro (where)?
	 */

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Categoria categoria = new Categoria();
		categoria.setId(2);

		String jpql = "select m from Movimentacao m join m.categorias c where c = :pCategoria";
		/*
		 * Testando esse TypedQuery para tirar o warning, existe uma api criteria tbm,
		 * procurar mais
		 */
		TypedQuery<Movimentacao> query = em.createQuery(jpql, Movimentacao.class);
		query.setParameter("pCategoria", categoria);

		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("id: " + movimentacao.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();
	}

}
