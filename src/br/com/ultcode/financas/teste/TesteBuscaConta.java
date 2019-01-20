package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteBuscaConta {

	public static void main(String[] args) {
		/*
		 * Já aprendemos a persistir um objeto no banco de dados, agora seria legal
		 * saber como buscar uma conta usando a jpa, essa classe se destina a isso Para
		 * isso, vamos precisar de uma instancia de um entity manager, como já
		 * encapsulamos, fica fácil. Também vamos precisar iniciar a transação, coisa
		 * que já aprendemos
		 */

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		/*
		 * O que iremos fazer aqui? Precisamos de uma conta para buscar por ela no BD,
		 * para buscar um conta no BD usando a JPA utilizamos o método find O que
		 * passamos nesse método? A entidade que queremos buscar como primeiro
		 * argumento. Passamos essa entidade Informando o nome da classe marcada como
		 * entity ponto class, para a JPA reconhecer essa entidade, e o ID (chave
		 * primaria definida nessa classe/entidade), que deve corresponder o tipo dessa
		 * ID. Como usamos um id do tipo integer lá na classe Conta, devemos ter uma
		 * busca passando um integer no segundo argumento do método find
		 */

		Conta conta = em.find(Conta.class, 1);
		System.out.println(conta.getTitular());

		/*
		 * Legal, tudo certo, fizemos um select com JPA no banco, retornou o regustro
		 * certo, agora vamo brincar um pouco O que acontece se alterarmos o nome do
		 * banco dentro dessa transação aqui no Código?
		 */

		conta.setBanco("Itau");

		/*
		 * Ao executarmos, vemos que a JPA fez um update no banco, e sim, isso é
		 * persistido no banco! E a gente simplesmente alterou com a propriedade com o
		 * método set Pq isso acontece? automaticamente a JPA sincronizou os dados do
		 * objeto conta com o BD. Isso acontece pq o método find retorna uma instancia
		 * de Conta que é considerado no estado managed, que é um estado das entidades
		 * da JPA que automaticamente os dados dessa entidade são sincronizados com os
		 * dados do BD Se alterarmos qualquer atributo dessa entidade dentro da
		 * transação, ela será sincronizada por ser managed (fiquei em duvida em como
		 * será feito isso com objetos que tem relacionamento ou composição)
		 */

		/*
		 * Se por acaso for setado uma propriedade que já é o que se encontra no banco a
		 * JPA simplesmente ignora isso, sem fazer de novo
		 */

		em.getTransaction().commit();
	}
}
