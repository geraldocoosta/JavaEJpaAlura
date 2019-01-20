package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteConta {

	public static void main(String[] args) {
		
		/*O que queremos fazer dentro dessa classe? persistir um objeto dessa classe em algum bd de maneira fácil
		 * então primeiramente, vamos popular um objeto dessa conta */
		Conta conta = new Conta();
		conta.setTitular("Geraldo");
		conta.setAgencia("123");
		conta.setBanco("Caixa Economica");
		conta.setNumero("456");
		
		/*Com nossa conta criada, preciso chamar a jpa para de fato persistir essa conta no banco de dados
		 * Para utilizar o jpa, precisamos de um objeto da classe EntityManager, classe principal para fazermos
		 * totas as alterações que a gente quiser fazer, e quem cria isso, é um EntityManagerFactory. Para criar
		 * o EntityManagerFactory, precisamos usar a classe persistence, que representa o xml persistence da pasta 
		 * META-INF. Usaremos o metodo createEntityManagerFactory passando a unidade de persistencia, que é um cara
		 * que agrupa as configurações feitas, pois podemos ter mais de uma, para conectar a banco de dados diferentes */
		
		
		EntityManager em = new JPAUtil().getEntityManager();
		
		/*Com o entitu manager criado, vamos usar ele para persistir, mas antes disso, devemos abrir uma transação e commitar 
		 * isso depois, para abrir a transação, usamos o método getTransaction(), que retorna um entityTransaction, 
		 * e usar os métodos begin e commit, antes e apos o uso da entidade. */
		
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		
		/*Depois de usados, devemos finalizar o entity manager factory e o entity manager criados, para não deixar o recurso aberto*/
		em.close();

	}


	/* OBS.: Como estou usando o mySQL driver mais novo, estorou o conhecido erro de time zone, tive que alterar o driver e também 
	 * colocar a linha para corrigir o timezone*/

	/* Apos algumas aulas, foi encapsulado a fabrica de EntityManager em outra classe para uma melhor organização */
}
