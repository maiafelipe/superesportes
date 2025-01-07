package edu.pw2.superesportes.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import edu.pw2.superesportes.model.atleta.Atleta;
import edu.pw2.superesportes.model.atleta.AtletaDados;
import edu.pw2.superesportes.model.atleta.AtletaResumoDados;
import jakarta.transaction.Transactional;
import edu.pw2.superesportes.model.atleta.AtletaRepository;

@RestController
@RequestMapping("/atletasrest")
public class AtletaRestController {

    @Autowired
    AtletaRepository atletaRepo;

    @PostMapping
    @Transactional
    public void saveAtleta(@RequestBody AtletaDados dados){
        Atleta a = new Atleta(dados);
        atletaRepo.save(a);
    }

    /*
    @GetMapping
    public List<AtletaResumoDados> getAtletas(){
        return atletaRepo.findAll().stream().map(AtletaResumoDados::new).toList();
    }
    */
    @GetMapping
    public Page<AtletaResumoDados> getAtletas(@PageableDefault(sort = {"nome"}, size = 10) Pageable paginacao){
        return atletaRepo.findAll(paginacao).map(AtletaResumoDados::new);
    }
}
