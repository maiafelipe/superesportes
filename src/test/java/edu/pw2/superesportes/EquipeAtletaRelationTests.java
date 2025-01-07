package edu.pw2.superesportes;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;

import edu.pw2.superesportes.model.atleta.Atleta;
import edu.pw2.superesportes.model.atleta.AtletaRepository;
import edu.pw2.superesportes.model.equipe.Equipe;
import edu.pw2.superesportes.model.equipe.EquipeRepository;
import jakarta.transaction.Transactional;

@SpringBootTest
public class EquipeAtletaRelationTests {

    @Autowired
    AtletaRepository atletaRepo;
    @Autowired
    EquipeRepository equipeRepo;

    @Test
    @Transactional
    @Commit
	void salvandoAtleta() {
        Atleta a = new Atleta("Ana", 21, 77.0, 1.80);
        atletaRepo.save(a);
	}

    @Test
    @Transactional
    @Commit
    void carregandoAtletas(){
        List<Atleta> atletas = atletaRepo.findAll();
        System.out.println("Lista: " + atletas);
    }

    @Test
    @Transactional
    @Commit
    void salvandoEquipe(){
        Equipe e = new Equipe("Vermelho", "Caucaia");
        equipeRepo.save(e);
        List<Atleta> atletas = atletaRepo.findAll();

        atletas.get(1).setEquipe(e);
        atletaRepo.save(atletas.get(1));

        atletas.get(3).setEquipe(e);
        atletaRepo.save(atletas.get(3));
    }

    @Test
    @Transactional
    @Commit
    void salvandoEquipeAlt(){
        Equipe e = new Equipe("Roxo", "Eusébio");
        //equipeRepo.save(e);
        List<Atleta> membros = new ArrayList<Atleta>();
        membros.add(new Atleta("José", 33, 55.5, 1.89));
        System.out.println("Membros: " + membros);
        e.setAtletas(membros);
        System.out.println("Equipe: " + e);
        equipeRepo.save(e);
    }

    @Test
    @Transactional
    @Commit
    void consultandoEquipes(){
        List<Equipe> equipes = equipeRepo.findAll();
        System.out.println("Equipes:" + equipes);
    }

    @Test
    @Transactional
    @Commit
    void removendoMembroDeEquipe(){
        Equipe e = equipeRepo.getReferenceById(15L);
        List<Atleta> membros = e.getAtletas();
        membros.get(0).setEquipe(null);
        System.out.println("Atleta: " + membros.get(0));
        atletaRepo.saveAllAndFlush(membros);
    }
}
