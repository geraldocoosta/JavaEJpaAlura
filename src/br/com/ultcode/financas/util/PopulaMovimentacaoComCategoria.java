package br.com.ultcode.financas.util;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Categoria;
import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.Movimentacao;
import br.com.ultcode.financas.modelo.TipoMovimentacao;

public class PopulaMovimentacaoComCategoria {
	public static void main(String[] args) {

		Categoria categoria1 = new Categoria();
		categoria1.setNome("Viagem");

		Categoria categoria2 = new Categoria();
		categoria2.setNome("Negocios");

		Conta conta = new Conta();
		conta.setId(2);

		Movimentacao mov1 = new Movimentacao();
		mov1.setData(Calendar.getInstance());
		mov1.setDescricao("Viagem a sp");
		mov1.setTipo(TipoMovimentacao.SAIDA);
		mov1.setValor(new BigDecimal("100"));
		mov1.setCategorias(Arrays.asList(categoria1, categoria2));
		mov1.setConta(conta);

		Movimentacao mov2 = new Movimentacao();
		mov2.setData(Calendar.getInstance());
		mov2.setDescricao("Viagem a rj");
		mov2.setTipo(TipoMovimentacao.SAIDA);
		mov2.setValor(new BigDecimal("300"));
		mov2.setCategorias(Arrays.asList(categoria1, categoria2));
		mov2.setConta(conta);

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		em.persist(categoria1);
		em.persist(categoria2);

		em.persist(mov1);
		em.persist(mov2);

		em.getTransaction().commit();
		em.close();
	}
}
