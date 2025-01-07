package edu.pw2.superesportes.model.equipe;

import java.util.List;

public record EquipeUpdateDados(Long id, String nome, String local, List<Long> membros) {
    
}
