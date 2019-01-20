package br.com.ultcode.financas.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

//Dando dica pro hibernate que ele vai precisar criar tabela para objetos dessa classe
// que vamos precisar administrar objetos dessa clasee
// essa dica � dada com a seguinte anota��o
@Entity
public class Conta {

	// Tota entidade no banco de dados tem uma chave, mostramos isso aqui com a @Id,
	// depois disso temos a possibilidade
	// de informar pro hibernate como esse valor vai ser gerenciado (auto increment,
	// sequence, etc)
	// com o argumento strategy=GenerationType.algo, esse argumento indica a
	// strategia de gerenciamento da chave
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String titular;
	private String numero;
	private String banco;
	private String agencia;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitular() {
		return titular;
	}

	public void setTitular(String titular) {
		this.titular = titular;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBanco() {
		return banco;
	}

	public void setBanco(String banco) {
		this.banco = banco;
	}

	public String getAgencia() {
		return agencia;
	}

	public void setAgencia(String agencia) {
		this.agencia = agencia;
	}

}