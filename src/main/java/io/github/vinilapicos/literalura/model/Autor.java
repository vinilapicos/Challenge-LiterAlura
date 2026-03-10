package io.github.vinilapicos.literalura.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer dataNascimento;
    private Integer dataFalecimento;

    @OneToMany(mappedBy = "autor", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Livro> livros = new ArrayList<>();

    public Autor(){
    }

    public Autor(DadosAutor dadosAutor){
        this.nome = dadosAutor.nome();
        this.dataNascimento = dadosAutor.dataNascimento();
        this.dataFalecimento = dadosAutor.dataFalecimento();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Integer dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Integer getDataFalecimento() {
        return dataFalecimento;
    }

    public void setDataFalecimento(Integer dataFalecimento) {
        this.dataFalecimento = dataFalecimento;
    }

    public List<Livro> getLivros() {
        return livros;
    }

    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }

    @Override
    public String toString(){

        String livrosCadastrados = livros.stream()
                .map(Livro::getTitulo)
                .collect(Collectors.joining(", "));
        return """
                Autor: %s
                Ano de nascimento: %d
                Ano de falecimento: %d
                Livros: [%s]
                """.formatted(
                        nome,
                (dataNascimento != null ? dataNascimento : "Desconhecido"),
                (dataFalecimento != null ? dataFalecimento : "Desconhecido"),
                (livrosCadastrados.isEmpty() ? "Nenhum livro registrado" : livrosCadastrados)
        );
    }
}



