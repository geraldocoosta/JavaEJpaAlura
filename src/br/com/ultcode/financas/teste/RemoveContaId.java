package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.util.JPAUtil;

public class RemoveContaId {

	public static void main(String[] args) {

		/*
		 * Agora aprenderemos a remover uma conta dado o seu id. Enfatizando que a
		 * tarefa do desenvolvedor ao trabalhar com JPA é transformar o status das
		 * entidades em Managed. Sabemos que no nosso banco tem um registro com id igual
		 * a 2. Vamos removelo usando o método remove em vez de persist. Porém, não
		 * podemos simplesmente popular nossa entidade com um id e remove-lo, precisamos
		 * primeiro que essa entidade seja managed para que possamos remove-la (até para
		 * removermos precisamos que uma entidade seja gereciavel). Com isso, podemos
		 * excluir um registro do nosso bd
		 */

		EntityManager em = new JPAUtil().getEntityManager();

		em.getTransaction().begin();

		Conta conta = em.find(Conta.class, 2);
		em.remove(conta);

		em.getTransaction().commit();
		em.close();
	}
}
