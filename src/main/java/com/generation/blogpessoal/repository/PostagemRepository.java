package com.generation.blogpessoal.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param; //import org.springframework.stereotype.Repository; REMOVIDO;

import com.generation.blogpessoal.model.Postagem;

//@Repository atualmente desnecessário
public interface PostagemRepository extends JpaRepository<Postagem, Long>{ //interface(class not) que auxilia na interação com o MySQL e herda a JPA, contém diversos Métodos pré-implementados para a geração de consultas SQL, que serão utilizadas para manipular os dados da entidade Postagem
//seus métodos são implementados na classe controller // <Postagem, Long> VEM COMO PARÂMETRO PARA A JPA, 1º CLASS POSTAGEM E 2º O LONG ID DENTRO DA POSTAGEM
	public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo")String titulo);
	
}
/*Na declaração public interface PostagemRepository extends JpaRepository<Postagem, Long>, a interface PostagemRepository estende a interface JpaRepository do Spring Data JPA, que é uma interface genérica que fornece métodos de acesso a dados comuns para uma entidade JPA.

Os dois parâmetros fornecidos à interface JpaRepository representam os tipos da entidade e do ID da entidade, respectivamente. No caso da interface PostagemRepository, a entidade é a classe Postagem e o ID é do tipo Long.

Ao fornecer esses tipos para a interface JpaRepository, o Spring Data JPA é capaz de gerar automaticamente implementações dos métodos básicos de acesso a dados, como save(), findById(), findAll(), deleteById(), entre outros, para a entidade Postagem. Isso permite que você escreva menos código boilerplate e se concentre mais na lógica de negócios da sua aplicação.*/

// A Interface JpaRepository contém todos os Métodos necessários para criação de um CRUD.

//Query Method são Métodos de Consulta personalizados, que permitem criar consultas específicas com qualquer Atributo da Classe associada a Interface Repositório (Postagem). Como a Interface JpaRepository possui apenas um Método de consulta específico pelo id (findById( Long id)), que é um Atributo comum em todas as Classes Model do Projeto, através das Query Methods podemos ampliar as nossas opções de consulta. As Query Methods são declaradas na Interface Repositório e implementadas nas Classes Controladoras e de Serviços (Service, que veremos mais a frente).

// AQUI QUE VAI FICAR AS QUERY METHODS, QUE SÃO FORMAS PERSONALIZADAS DE SE PESQUISAR

//Query Methods fazem são criar instruções SQL através de Palavras Chave, que combinadas com os Atributos da Classe, geram consultas personalizadas

/*ex: public Optional <Postagem> findByTitulo(String titulo); equivale a SELECT * FROM tb_postagens WHERE titulo = "titulo";*/

/*	find			🡪	SELECT
	By				🡪	WHERE
	Titulo			🡪	Atributo da Classe Postagem
	String titulo	🡪	Parâmetro do Método contendo o título que você deseja procurar.*/

/*ex: Query Method

public List <Postagem> findAllByTituloContainingIgnoreCase(@Param("titulo") String titulo);

Instrução SQL equivalente

SELECT * FROM tb_postagens WHERE titulo LIKE "%titulo%";*/

/*	find				🡪	SELECT
	All					🡪	*
	By					🡪	WHERE
	Titulo				🡪	Atributo da Classe Postagem
	Containing			🡪	LIKE "%titulo%"
	IgnoreCase			🡪	Ignorando letras maiúsculas ou minúsculas
	@Param("titulo")	🡪	Define a variável String titulo como um parâmetro da consulta. Esta anotação é obrigatório em consultas do tipo Like.
	String titulo		🡪	Parâmetro do Método contendo o título que você deseja procurar.*/

/*Consultar mais opções aqui https://github.com/rafaelq80/cookbook_spring_v3/blob/main/03_spring/guia_jpa.md*/


