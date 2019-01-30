package br.com.ultcode.financas.teste;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.TipoMovimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteFuncoesJPQL2 {
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta and"
				+ " m.tipo = :pTipo order by m.valor desc";

		/* a função avg retorna double */
		String jpql2 = "select avg(m.valor) from Movimentacao m where m.conta = :pConta and"
				+ " m.tipo = :pTipo order by m.valor desc";

		Query query = em.createQuery(jpql);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		BigDecimal soma = (BigDecimal) query.getSingleResult();

		System.out.println("A soma é = " + soma);

		query = em.createQuery(jpql2);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		Double media = (Double) query.getSingleResult();

		System.out.println("A media é = " + media);

		em.getTransaction().commit();
		em.close();
	}
}
