package edu.pw2.superesportes.controller;

import edu.pw2.superesportes.model.atleta.AtletaUpdateDados;
import edu.pw2.superesportes.model.equipe.Equipe;
import edu.pw2.superesportes.model.equipe.EquipeRepository;
import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import edu.pw2.superesportes.model.atleta.Atleta;
import edu.pw2.superesportes.model.atleta.AtletaDados;
import edu.pw2.superesportes.model.atleta.AtletaRepository;

@Controller
@RequestMapping("/atletas")
public class AtletaController {

    @Autowired
    private AtletaRepository atletaRepo;
    @Autowired
    private EquipeRepository equipeRepo;

    @GetMapping("/cadastro")
    public String loadAtletaForm(Long id, Model model){
        if (id != null) {
            Atleta a1 = atletaRepo.getReferenceById(id);
            model.addAttribute("atleta", a1);
        }
        List<Equipe> equipes = equipeRepo.findAll();
        model.addAttribute("equipes", equipes);
        return "atleta/cadastro";
    }

    @GetMapping
    public String loadAtletaList(Model model){
        model.addAttribute("lista", atletaRepo.findAll());
        return "atleta/listagem";
    }


    @PostMapping
    @Transactional
    public String registrarAtleta(AtletaDados dados){
        Atleta a1 = new Atleta(dados);
        if(dados.equipe() != 0){
            a1.setEquipe(equipeRepo.getReferenceById(dados.equipe()));
        }
        atletaRepo.save(a1);
        return "redirect:/atletas";
    }

    @DeleteMapping
    @Transactional
    public String deletarAtleta(Long id){
        atletaRepo.deleteById(id);
        return "redirect:/atletas";
    }

    @PutMapping
    @Transactional
    public String atualizaAtleta(AtletaUpdateDados dados){
        Atleta a1 = atletaRepo.getReferenceById(dados.id());
        a1.atualizaAtleta(dados);
        if(dados.equipe() != 0){
            a1.setEquipe(equipeRepo.getReferenceById(dados.equipe()));
        } else {
            a1.setEquipe(null);
        }
        return "redirect:/atletas";
    }

  
}
