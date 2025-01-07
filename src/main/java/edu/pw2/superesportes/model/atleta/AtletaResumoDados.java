package edu.pw2.superesportes.model.atleta;

public record AtletaResumoDados (String nome, Integer idade) {
    public AtletaResumoDados(Atleta atleta){
        this(atleta.getNome(), atleta.getIdade());
    }
}
