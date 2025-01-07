package edu.pw2.superesportes.model.atleta;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import edu.pw2.superesportes.model.equipe.Equipe;

public interface AtletaRepository extends JpaRepository<Atleta, Long>{
    @Query("SELECT a FROM Atleta a WHERE a.equipe is null")
    public List<Atleta> getAtletasSemEquipe();

    @Query("SELECT a FROM Atleta a WHERE a.equipe = :equipe")
    public List<Atleta> getAtletasByEquipe(@Param("equipe") Equipe equipe);
}
