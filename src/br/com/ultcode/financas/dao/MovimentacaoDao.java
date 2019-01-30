package br.com.ultcode.financas.dao;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.TipoMovimentacao;

public class MovimentacaoDao {

	private EntityManager em;

	public MovimentacaoDao(EntityManager em) {
		this.em = em;
	}

	public List<BigDecimal> getMediasPorDiaETipo(TipoMovimentacao tipo, Conta conta) {
		String jpql = "select sum(m.valor) from Movimentacao m where m.conta = :pConta and m.tipo = :pTipo"
				+ " group by day(m.data), month(m.data), year(m.data)";

		TypedQuery<BigDecimal> query = em.createQuery(jpql, BigDecimal.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", tipo);

		return (List<BigDecimal>) query.getResultList();
	}

}
