package br.com.ultcode.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteTodasMovimentacoesDasContas {
	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		/*
		 * Cardinalidades ToMany tem carregamento lazy, ou seja, a jpa s� faz a busca
		 * por esses tipos quando for realmente chamada, ou seja, se fizermos a busca
		 * por contas e depois pelas movimentacoes dessas contas, quando fizermos o
		 * getMovimentacoes que a jpa vai fazer o select. H� formas de fazer a jpa fazer
		 * essas buscas de uma vez s�. Nesse caso, fazemos isso com o join fetch. Com o
		 * join fetch dizemos para buscar mais de um entidade no banco
		 */
		/*
		 * Por padr�o, o join da JPA � o innerjoin, para selecionar outro join, basta
		 * escrever o left ou o rigth a esquerda do join, o distinct � para melhorar o
		 * select, pois sem este, vai trazer registros que duplicam o nome do titular, e
		 * o left join � para trazer todos os titulares, mesmo que n�o tenham nenhuma
		 * movimenta��o associada, lembrando que inner join � igual a interse��o
		 * (produto cartesiano) da algebra relacional.
		 */

		String jpql = "select distinct c from Conta c left join fetch c.movimentacoes";

		Query query = em.createQuery(jpql);
		List<Conta> todasAsContas = query.getResultList();

		for (Conta conta : todasAsContas) {
			System.out.println("Titular: " + conta.getTitular());
			System.out.println("Movimentacoes: ");
			System.out.println(conta.getMovimentacoes());
		}
	}
}
