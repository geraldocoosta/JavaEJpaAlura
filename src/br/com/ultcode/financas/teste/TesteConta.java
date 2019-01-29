package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {

		/*
		 * O que queremos fazer dentro dessa classe? persistir um objeto dessa classe em
		 * algum bd de maneira f�cil ent�o primeiramente, vamos popular um objeto dessa
		 * conta
		 */
		Conta conta = new Conta();
		conta.setTitular("Geraldo");
		conta.setAgencia("123");
		conta.setBanco("Caixa Economica");
		conta.setNumero("456");

		/*
		 * Se a aplica��o morrer aqui e essa entidade nao for pesistida, o que acontece?
		 * Essa conta n�o para no banco, esse estado � chamado transient, a conta acabou
		 * de ser criada e ela n�o vai ser pesistida, o que faz uma entidade virar
		 * management � dar um find ou um persist
		 * Objetivo do m�todo persist � pegar uma entidade e transformala em managed
		 * H� um problema em fazer isso, que � a consulta no banco, vai ser uma pra inserir
		 * e outra para atualizar, mas em quest�o de aprendizado, tudo certo
		 */

		/*
		 * Com nossa conta criada, preciso chamar a jpa para de fato persistir essa
		 * conta no banco de dados Para utilizar o jpa, precisamos de um objeto da
		 * classe EntityManager, classe principal para fazermos totas as altera��es que
		 * a gente quiser fazer, e quem cria isso, � um EntityManagerFactory. Para criar
		 * o EntityManagerFactory, precisamos usar a classe persistence, que representa
		 * o xml persistence da pasta META-INF. Usaremos o metodo
		 * createEntityManagerFactory passando a unidade de persistencia, que � um cara
		 * que agrupa as configura��es feitas, pois podemos ter mais de uma, para
		 * conectar a banco de dados diferentes
		 */

		EntityManager em = new JPAUtil().getEntityManager();

		/*
		 * Com o entitu manager criado, vamos usar ele para persistir, mas antes disso,
		 * devemos abrir uma transa��o e commitar isso depois, para abrir a transa��o,
		 * usamos o m�todo getTransaction(), que retorna um entityTransaction, e usar os
		 * m�todos begin e commit, antes e apos o uso da entidade.
		 */

		em.getTransaction().begin();
		em.persist(conta);
		
		em.getTransaction().commit();

		/*
		 * Depois de usados, devemos finalizar o entity manager factory e o entity
		 * manager criados, para n�o deixar o recurso aberto
		 */
		em.close();

	}

	/*
	 * OBS.: Como estou usando o mySQL driver mais novo, estorou o conhecido erro de
	 * time zone, tive que alterar o driver e tamb�m colocar a linha para corrigir o
	 * timezone
	 */

	/*
	 * Apos algumas aulas, foi encapsulado a fabrica de EntityManager em outra
	 * classe para uma melhor organiza��o
	 */
	
	/* Refor�ando que o estado Managed s� ir� durar enquanto n�o fecharmos o EntityManager*/
}
