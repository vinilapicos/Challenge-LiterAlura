
# Challenge LiterAlura

![Status: Concluído](https://img.shields.io/badge/status-concluído-brightgreen)
![Tecnologias](https://img.shields.io/badge/Java-orange?style=flat&logo=openjdk)

<br>

## 📜 Índice

1.  [Sobre o Projeto](#1-sobre-o-projeto)
2.  [Principais Funcionalidades](#2-principais-funcionalidades)

<br>

## 1. Sobre o Projeto

O *challenge* LiterAlura tem como objetivo desenvolver um Catálogo de Livros que ofereça interação textual (via console) com os usuários, proporcionando no mínimo 5 opções de interação. Os livros são buscados por meio de uma API externa específica.

<br>

## 2. Principais Funcionalidades

| Funcionalidade | Descrição |
| :--- | :--- |
| **Exibição de Menu** | O LiterAlura exibe um menu trazendo todas as opções possíveis para o usuário, utilizando laços de repetição (`while`) e `switch case` para manter a aplicação em execução contínua. |
| **Consumo de API**| O LiterAlura consome a API Gutendex, que simula a biblioteca do Projeto Gutenberg, disponibilizando um enorme catálogo de livros. |
| **Persistência no Banco de Dados** | A aplicação realiza a conexão com um banco de dados PostgreSQL utilizando o Spring Data JPA, salvando todos os livros buscados e, consequentemente, os seus respectivos autores. |
| **Buscar livro pelo título**| A aplicação utiliza o título inserido pelo usuário para realizar uma requisição à API, retornando o livro mais popular com o nome correspondente e salvando seus dados no banco. |
| **Listar livros registrados**| A aplicação realiza uma consulta ao banco de dados e retorna todos os livros (e seus dados) já registrados no sistema. |
| **Listar autores registrados**| A aplicação realiza uma consulta ao banco de dados e retorna todos os autores (e seus dados) já registrados no sistema. |
| **Listar autores vivos em um determinado ano**| A aplicação executa uma consulta avançada com `@Query` para filtrar e retornar os autores que estavam vivos no ano inserido pelo usuário. |
| **Listar livros em um determinado idioma**| A aplicação realiza uma busca no banco de dados, filtrando o campo Idioma (que é gerenciado como um `Enum`), e retorna todos os livros que correspondem à escolha do usuário. |
