package br.com.ultcode.financas.teste;

import javax.persistence.EntityManager;

import br.com.ultcode.financas.modelo.Cliente;
import br.com.ultcode.financas.util.JPAUtil;

public class TestaContaCliente {
	public static void main(String[] args) {
		
		Cliente cliente = new Cliente();
		cliente.setNome("Geraldo");
		cliente.setProfissao("Estagiario");
		cliente.setEndereco("Gama");
		
		EntityManager em = new JPAUtil().getEntityManager();
		em.getTransaction().begin();
		em.getTransaction().commit();
		em.close();
	}
}
