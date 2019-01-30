package br.com.ultcode.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.TipoMovimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteUsandoNamedQuery {
	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setId(2);
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		
		TypedQuery<BigDecimal> query = em.createNamedQuery("mediaPorDiaETipo", BigDecimal.class);
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.SAIDA);
		
		List<BigDecimal> medias = query.getResultList();
	
		System.out.println("AS medias: " + medias);
		
		em.getTransaction().commit();
	}
}
