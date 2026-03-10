package io.github.vinilapicos.literalura.model;

public enum Idioma {
    PT("pt", "portugues"),
    EN("en", "ingles"),
    ES("es", "espanhol"),
    FR("fr", "frances");

    private String siglaGutendex;
    private String nomePorExtenso;

    Idioma(String siglaGutendex, String nomePorExtenso) {
        this.siglaGutendex = siglaGutendex;
        this.nomePorExtenso = nomePorExtenso;
    }

    public String getSiglaGutendex() {
        return siglaGutendex;
    }

    public String getNomePorExtenso() {
        return nomePorExtenso;
    }

    public static Idioma fromString(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.siglaGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado para a sigla: " + text);
    }

    public static Idioma fromNomeExtenso(String text) {
        for (Idioma idioma : Idioma.values()) {
            if (idioma.nomePorExtenso.equalsIgnoreCase(text) || idioma.siglaGutendex.equalsIgnoreCase(text)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Nenhum idioma encontrado para a string: " + text);
    }
}
