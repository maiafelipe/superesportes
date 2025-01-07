package edu.pw2.superesportes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.pw2.superesportes.model.atleta.Atleta;
import edu.pw2.superesportes.model.atleta.AtletaRepository;
import edu.pw2.superesportes.model.equipe.Equipe;
import edu.pw2.superesportes.model.equipe.EquipeDados;
import edu.pw2.superesportes.model.equipe.EquipeRepository;
import edu.pw2.superesportes.model.equipe.EquipeUpdateDados;
import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/equipes")
public class EquipeController {
    @Autowired
    private EquipeRepository equipeRepo;
    @Autowired
    private AtletaRepository atletaRepo;

    @GetMapping
    public String loadEquipeList(Model model){
        List<Equipe> equipes = equipeRepo.findAll();
        model.addAttribute("equipes", equipes);
        return "equipe/listagem";
    }

    @GetMapping("/cadastro")
    public String loadEquipeForm(Long id, Model model){
        List<Atleta> atletas = atletaRepo.getAtletasSemEquipe();
        if (id != null) {
            Equipe e = equipeRepo.getReferenceById(id);
            atletas.addAll(atletaRepo.getAtletasByEquipe(e));
            model.addAttribute("equipe", e);
        }
        model.addAttribute("atletas", atletas);
        return "equipe/cadastro";
    }

    @PostMapping
    @Transactional
    public String registrarEquipe(EquipeDados dados){
        List<Atleta> membros = atletaRepo.findAllById(dados.membros());
        Equipe e = new Equipe(dados.nome(), dados.local());
        e.setAtletas(membros);
        equipeRepo.save(e);
        for (Atleta atleta : membros) {
            atleta.setEquipe(e);
            atletaRepo.save(atleta);
            System.out.println("Atleta: " + atleta);
        }
        return "redirect:/equipes";
    }

    @DeleteMapping
    @Transactional
    public String deletarEquipe(Long id){
        Equipe e = equipeRepo.getReferenceById(id);
        for (Atleta a : e.getAtletas()) {
            a.setEquipe(null);
        }
        e.getAtletas().clear();
        equipeRepo.delete(e);
        return "redirect:/equipes";
    }

    @PutMapping
    @Transactional
    public String atualizaEquipe(EquipeUpdateDados dados){
        Equipe e = equipeRepo.getReferenceById(dados.id());
        e.setNome(dados.nome());
        e.setLocal(dados.local());
        for (Atleta a : e.getAtletas()) {
            a.setEquipe(null);
        }
        e.getAtletas().clear();
        if(dados.membros()!=null){
            List<Atleta> novosMembros = atletaRepo.findAllById(dados.membros()); 
            for (Atleta m : novosMembros) {
                m.setEquipe(e);
            }
        }
        return "redirect:/equipes";
    }


}
