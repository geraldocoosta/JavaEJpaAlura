package br.com.ultcode.financas.teste;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.ultcode.financas.modelo.Conta;
import br.com.ultcode.financas.modelo.Movimentacao;
import br.com.ultcode.financas.modelo.TipoMovimentacao;
import br.com.ultcode.financas.util.JPAUtil;

public class TesteJPQL {
	public static void main(String[] args) {

		/*
		 * Quando adotamos o JPA, queremos nos distanciar ao m�ximo do modelo
		 * relacional, focar exclusivamente no modelo orientado a objetos, seus estados
		 * e intera��es, Portanto, � interessante pararmos de utilizar o SQL para estas
		 * consultas ou altera��es, optando por uma linguagem espec�fica do JPA bem
		 * parecida, o JPQL (Java Persistence Query Language), que � o que utilizaremos
		 * para manipularmos nossos dados de forma orientada a objetos.
		 */

		/*
		 * Com o jpql, n�s n�o nos referenciamos as colunas da tabela, e sim a objetos,
		 * afinal, estamos trabalhando com um abstra��o justamente para tirar esse
		 * conhecimento do desenvolvedor
		 */

		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();

		Conta conta = new Conta();
		conta.setId(6);

		/*
		 * com dois ponto algo podemos definir um parametro, que pode ser at� um objeto,
		 * mas isso � definido apos a cria��o do objeto query (tem um principio parecido
		 * com os params de rota do angular, com isso de dois pontos), o p antes � uma
		 * conven��o, significa parametro
		 */
		/*
		 * Lembrando que nesse jpql estamos nos referindo n�o a tabela e sim a entidade
		 * e seus atributos
		 */
		String jpql = "select m from Movimentacao m where m.conta = :pConta" + " and m.tipo = :pTipo "
				+ " order by m.valor desc";
		Query query = em.createQuery(jpql);
		// primeiro argumento, nome do argumento, segundo o argumento a ser passado
		query.setParameter("pConta", conta);
		query.setParameter("pTipo", TipoMovimentacao.ENTRADA);
		List<Movimentacao> movimentacoes = query.getResultList();

		for (Movimentacao movimentacao : movimentacoes) {
			System.out.println("Descricao: " + movimentacao.getDescricao());
			System.out.println("id: " + movimentacao.getConta().getId());
		}

		em.getTransaction().commit();
		em.close();

		/*
		 * Isso � mais vantajoso por trazer uma query mais f�cil de ser escrita e, mais
		 * uma vez, por estar mais pr�xima ao modelo orientado a objetos. Al�m disto, h�
		 * a quest�o de portabilidade: n�o precisaremos trocar a implementa��o do JPA
		 * (de Hibernate para EclipseLink, por exemplo). A forma de escrita de JPQL n�o
		 * muda, pois isto faz parte de sua especifica��o, que � de padronizar.
		 */
	}
}
