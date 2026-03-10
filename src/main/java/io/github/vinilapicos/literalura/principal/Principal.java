package io.github.vinilapicos.literalura.principal;

import io.github.vinilapicos.literalura.model.*;
import io.github.vinilapicos.literalura.repository.AutorRepository;
import io.github.vinilapicos.literalura.repository.LivroRepository;
import io.github.vinilapicos.literalura.service.ConsumoApi;
import io.github.vinilapicos.literalura.service.ConverteDados;

import java.util.Optional;
import java.util.Scanner;

public class Principal {
    private ConsumoApi consumo = new ConsumoApi();
    private Scanner sc = new Scanner(System.in);
    private ConverteDados conversor = new ConverteDados();
    private LivroRepository livroRepository;
    private AutorRepository autorRepository;

    private final String ENDERECO = "https://gutendex.com/books?search=";

    public Principal(LivroRepository livroRepository, AutorRepository autorRepository){
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
    }

    public void exibeMenu(){
        var opcao = -1;
        while (opcao != 0){
            var menu = """
                    1 - Buscar livro pelo título
                    2 - Listar livros registrados
                    3 - Listar autores registrados
                    4 - Listar autores vivos em um determinado ano
                    5 - Listar livros em um determinado idioma
                    
                    0 - Sair
                    """;
            System.out.println(menu);
            var entrada = sc.nextLine();
            try{
                opcao = Integer.parseInt(entrada);
            } catch (NumberFormatException e){}

            switch (opcao){
                case 1:
                    buscarLivroPorTitulo();
                    break;
                case 2:
                    listarLivrosRegistrados();
                    break;
                case 3:
                    listarAutoresRegistrados();
                    break;
                case 4:
                    listarAutoresVivosNoAno();
                    break;
                case 5:
                    listarLivrosPorIdioma();
                    break;
                case 0:
                    System.out.println("""
                            Você escolheu sair! Obrigado por utilizar a LiterAlura.
                            Até breve...
                            """);
                    break;
                default:
                    System.out.println("Opção inválida, tente novamente ou digite 0 para sair!");

            }
        }
    }

    private void buscarLivroPorTitulo() {
        DadosLivro dados = getDadosLivro();

        if (dados != null) {

            var livroNoBanco = livroRepository.findByTituloIgnoreCase(dados.titulo());

            if (livroNoBanco.isPresent()){
                System.out.println("O livro buscado já está cadastrado!");
                return;
            }
            Livro livro = new Livro(dados);
            DadosAutor dadosAutor = dados.autor().get(0);
            var autorNoBanco = autorRepository.findByNomeIgnoreCase(dadosAutor.nome());
            Autor autor;
            if (autorNoBanco.isPresent()) {
                autor = autorNoBanco.get();
                System.out.println("Autor ja existente encontrado: " + autor.getNome());
            } else {
                autor = new Autor(dadosAutor);
                autorRepository.save(autor);
                System.out.println("Novo autor cadastrado: " + autor.getNome());
            }

            livro.setAutor(autor);
            livroRepository.save(livro);

            System.out.println(livro);
        }
    }

    private void listarLivrosRegistrados(){
        var livros = livroRepository.findAll();

        if (livros.isEmpty()){
            System.out.println("Ainda não há nenhum livro cadastrado, cadastre um na opção 1!");
        } else{
            System.out.println("------ Livros Registrados ------");
            livros.forEach(System.out::println);
        }
    }

    private void listarAutoresRegistrados(){
        var autores = autorRepository.findAll();

        if (autores.isEmpty()){
            System.out.println("Ainda não há nenhum autor cadastrado, cadastre um livro na opção 1!");
        } else {
            System.out.println("------ Autores Registrados ------");
            autores.forEach(System.out::println);
        }
    }

    private void listarAutoresVivosNoAno(){
        System.out.println("Digite o ano que deseja pesquisar: ");
        var entrada = sc.nextLine();

        try{
            var ano = Integer.parseInt(entrada);
            var autoresVivos = autorRepository.buscarAutoresVivosNoAno(ano);

            if (autoresVivos.isEmpty()){
                System.out.println("Nenhum autor vivo foi encontrado :c");
            } else {
                System.out.println("---- Autores Vivos No Ano " + ano + " ----");
                autoresVivos.forEach(System.out::println);
            }
        } catch (NumberFormatException e){
            System.out.println("Ano digitado foi inválido, digite apenas números (yyyy).");
        }
    }

    private void listarLivrosPorIdioma(){
        System.out.println("Digite o idioma que deseja buscar (portugues, ingles, espanhol ou frances)");
        var idiomaBusca = sc.nextLine().toLowerCase();

        try {
            Idioma idiomaEnum = Idioma.fromNomeExtenso(idiomaBusca);
            var livros = livroRepository.findByIdioma(idiomaEnum);

            if (livros.isEmpty()){
                System.out.println("Não existem livros neste idioma cadastrados.");
            } else {
                System.out.println("---- Livros em " + idiomaEnum.name() + "----");
                livros.forEach(System.out::println);
            }
        } catch (IllegalArgumentException e) {
            System.out.println("Idioma não reconhecido, por favor tente um dos idiomas listados!");
        }
    }

    private DadosLivro getDadosLivro(){
        System.out.println("Insira o título do livro que você deseja procurar: ");
        var tituloLivro = sc.nextLine();
        var json = consumo.obterDados(ENDERECO + tituloLivro.replace(" ", "%20"));
        DadosResposta dadosResposta = conversor.obterDados(json, DadosResposta.class);
        if (dadosResposta.resultados() != null && !dadosResposta.resultados().isEmpty()){
            return dadosResposta.resultados().get(0);
        } else {
            System.out.println("Nenhum livro encontrado com o titulo: " + tituloLivro);
            return null;
        }
    }
}
