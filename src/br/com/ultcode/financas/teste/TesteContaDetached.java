package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteContaDetached {

	public static void main(String[] args) {

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();
		Conta conta = em.find(Conta.class, 6);

		conta.setTitular("Romario");

		em.getTransaction().commit();

		em.close();

		/* ================================================================ */
		/*
		 * Nesse outro EntityManager, iremos tentar manipular a conta que já foi
		 * persistida em outro EM e persistir essas alterações, ao tentar fazer isso com
		 * o código abaixo, irá dar um erro, que será o PersistentObjectException O que
		 * isso quer dizer? Basicamente, após fechado o entityManager, o objeto que era
		 * maneged vira detached, com isso, não é possivel manipular dessa forma. Como
		 * resolver então?
		 */
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		conta.setTitular("TeTe");
		em2.merge(conta);

		em2.getTransaction().commit();
		em2.close();

	}

	/*
	 * Trata-se do estado Detached, em que a conta não é mais gerenciada pelo JPA.
	 * Há uma representação sua no banco, no entanto a sincronização automática não
	 * está ativada, pois o gerenciamento não ocorre mais.
	 */

	/*
	 * Quando temos uma entidade que já foi managed ainda, e ainda estamos usando
	 * esta para fazer alguma alteração, podemos usar o método merge do
	 * entinyManager para conseguir persistir e fazer uma mescla entre a entidade
	 * que já foi persistida e a entidade detached que ainda temos em memoria na
	 * aplicação, com isso rodará um update, mas não é bom associar isso, é melhor
	 * lembrar que a função é fazer uma mescla entre uma entidade que foi persistida
	 * e um objeto modificado que já foi persistido e já foi managed
	 * detached --> merge() --> managed
	 */
}
