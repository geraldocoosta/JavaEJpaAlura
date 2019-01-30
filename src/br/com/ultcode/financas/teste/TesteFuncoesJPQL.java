package br.com.ultcode.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.TipoMovimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);

		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo"
				+ " group by day(m.data), month(m.data), year(m.data)";

		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);

		List<BigDecimal> medias = (List<BigDecimal>) query.getResultList();

		System.out.println("A média dos valores por dia é = " + medias);

		em.getTransaction().commit();
		em.close();
	}
}
