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
		 * Nesse outro EntityManager, iremos tentar manipular a conta que j� foi
		 * persistida em outro EM e persistir essas altera��es, ao tentar fazer isso com
		 * o c�digo abaixo, ir� dar um erro, que ser� o PersistentObjectException O que
		 * isso quer dizer? Basicamente, ap�s fechado o entityManager, o objeto que era
		 * maneged vira detached, com isso, n�o � possivel manipular dessa forma. Como
		 * resolver ent�o?
		 */
		EntityManager em2 = new JPAUtil().getEntityManager();
		em2.getTransaction().begin();

		conta.setTitular("TeTe");
		em2.merge(conta);

		em2.getTransaction().commit();
		em2.close();

	}

	/*
	 * Trata-se do estado Detached, em que a conta n�o � mais gerenciada pelo JPA.
	 * H� uma representa��o sua no banco, no entanto a sincroniza��o autom�tica n�o
	 * est� ativada, pois o gerenciamento n�o ocorre mais.
	 */

	/*
	 * Quando temos uma entidade que j� foi managed ainda, e ainda estamos usando
	 * esta para fazer alguma altera��o, podemos usar o m�todo merge do
	 * entinyManager para conseguir persistir e fazer uma mescla entre a entidade
	 * que j� foi persistida e a entidade detached que ainda temos em memoria na
	 * aplica��o, com isso rodar� um update, mas n�o � bom associar isso, � melhor
	 * lembrar que a fun��o � fazer uma mescla entre uma entidade que foi persistida
	 * e um objeto modificado que j� foi persistido e j� foi managed
	 * detached --> merge() --> managed
	 */
}
