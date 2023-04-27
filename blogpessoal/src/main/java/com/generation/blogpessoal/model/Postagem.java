package com.generation.blogpessoal.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity //INICIA O DB NA CLASS
@Table(name ="tb_postagens") //SEM ELA, O DB TERÁ O NOME DA CLASS, AS DUAS ANOTAÇÕES VÊEM APRESENTANDO COMO A CLASS SERÁ, MESMA REGRINHA DO INGLÊS, TB É IMPORTANTE
public class Postagem { //cria o banco de dados
	
	@Id //INDICA A CHAVE A PK DA APLICAÇÃO
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@ DIZ QUE O MYSQL GERARÁ A PK, STRATEGY FALA DEPOIS DO = COMO ESSA CHAVE SERÁ GERADA, SENDO IDENTITY, POR AUTOINCREMENTO
	private Long id;
	
	@NotBlank(message = "O atribuído tíulo é Obrigatório!") // @NotBlank não permite que o Atributo seja Nulo ou contenha apenas espaços em branco. Você pode configurar uma mensagem para o usuário através do Atributo message.
	@Size(min = 5, max = 100, message = "O atribuído título deve conter no mínimo 05 e no máximo 100 caracteres")
	private String titulo;
	
	@NotBlank(message = "O atribuído texto é Obrigatório!")
	@Size(min = 10, max = 1000, message = "O atribuído texto deve conter no mínimo 10 e no máximo 1000 caracteres") // A anotação @Size define o valor Mínimo (min) e o valor Máximo (max) de caracteres do Atributo. Não é obrigatório configurar os 2 parâmetros. Como o parâmetro max foi configurado, observe que o mesmo valor informado será inserido na definição dos Atributos titulo (varchar(100)) e texto (varchar(1000)) na tabela tb_postagens no Banco de dados. Você pode configurar uma mensagem para o usuário através do Atributo message.
	private String texto;
	
	@UpdateTimestamp //AUTOMATIZA A DATA DA APLICAÇÃO PARA A DATA DO SISTEMA
	private LocalDateTime data;
	
	@ManyToOne
	@JsonIgnoreProperties("postagem") // importante pra não exibir várias vezes a mesma informação, colocar o local onde se está
	private Tema tema; // não precisaria chamar, pois estão no mesmo pacote, já são da família
	
	@ManyToOne
	@JsonIgnoreProperties("postagem")
	private Usuario usuario;
	
	// = FOREIGN KEY (TEMA_ID) REFERENCE TB_TEMA.ID
	
	// RELACIONANDO:
	// 1º colocar o @ManyToOne (da relação de que se trata, levando em consideração da atual para a outra)
	// 2º Colocar o @JsonIgnoreProperties("nomeDaClassAtual")
	// 3º Colocar Getters and Setters da Tema.
	// 4º FAZER DE TEMA PARA POSTAGEM TAMBÉM.

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	// TEMA:
	
	public Tema getTema() {
		return tema;
	}

	public void setTema(Tema tema) {
		this.tema = tema;
	}

	// USUÁRIO:
	
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	
	
	
}
