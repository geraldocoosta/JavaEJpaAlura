package br.com.ultcode.financas.teste;

import java.math.BigDecimal;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.Movimentacao;
import br.com.ultcode.financas.modelo.TipoMovimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteJpaRelacionamento {

	public static void main(String[] args) {
		Conta conta = new Conta();
		conta.setTitular("Geraldo");
		conta.setBanco("Caixa");
		conta.setNumero("458");
		conta.setAgencia("654");
		
		Movimentacao mov = new Movimentacao();
		mov.setConta(conta);
		mov.setData(Calendar.getInstance());
		mov.setDescricao("Recebendo grana");
		mov.setTipo(TipoMovimentacao.ENTRADA);
		mov.setValor(new BigDecimal("200.0"));
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		/*
		 * Para usarmos a cardinalidade criada, devemos persistir primeiro uma conta
		 * para depois inserir uma movimenta��o, pois, a movimenta��o precisa da conta
		 * para existir, j� a conta n�o precisa do movimenta��o
		 */
		
		em.persist(conta);
		em.persist(mov);
		
		em.getTransaction().commit();
		em.close();
	}
}
