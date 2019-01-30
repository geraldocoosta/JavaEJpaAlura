package br.com.ultcode.financas.teste;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.dao.MovimentacaoDao;
import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.TipoMovimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteFuncoesJPQL {
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(2);
		
		MovimentacaoDao dao = new MovimentacaoDao(em);

		List<BigDecimal> medias = dao.getMediasPorDiaETipo(TipoMovimentacao.SAIDA, conta);

		System.out.println("A média dos valores por dia é = " + medias);

		em.getTransaction().commit();
		em.close();
	}
}
