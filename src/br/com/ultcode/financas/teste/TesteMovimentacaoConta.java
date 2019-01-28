package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.Movimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteMovimentacaoConta {
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Movimentacao movimentacao = em.find(Movimentacao.class, 3);
		Conta conta = movimentacao.getConta();

		System.out.println(conta.getTitular());
		
		System.out.println(conta.getMovimentacoes());

		em.getTransaction().commit();
		em.close();
	}
}
