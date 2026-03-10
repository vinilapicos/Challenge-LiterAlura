package io.github.vinilapicos.literalura.model;


import jakarta.persistence.*;

@Entity
@Table(name = "livros")
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;

    @ManyToOne
    private Autor autor;
    @Enumerated(EnumType.STRING)
    private Idioma idioma;
    private Integer numeroDownloads;

    public Livro(){

    }

    public Livro(DadosLivro dadosLivro){
        this.titulo = dadosLivro.titulo();

        if(dadosLivro.idiomas() != null && !dadosLivro.idiomas().isEmpty()){
            this.idioma = Idioma.fromString(dadosLivro.idiomas().get(0));
        }

        this.numeroDownloads = dadosLivro.numeroDownloads();
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public Idioma getIdioma() {
        return idioma;
    }

    public void setIdioma(Idioma idioma) {
        this.idioma = idioma;
    }

    public Integer getNumeroDownloads() {
        return numeroDownloads;
    }

    public void setNumeroDownloads(Integer numeroDownloads) {
        this.numeroDownloads = numeroDownloads;
    }
    @Override
    public String toString(){
        return """
                ----- LIVRO -----
                Título: %s
                Autor: %s
                Idioma: %s
                Número de dowloads: %d
                -----------------
                """.formatted(
                        titulo,
                (autor != null ? autor.getNome() : "Autor desconhecido"),
                idioma,
                numeroDownloads);
    }
}

