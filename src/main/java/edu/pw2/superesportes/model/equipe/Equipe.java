package edu.pw2.superesportes.model.equipe;

import java.util.List;

import edu.pw2.superesportes.model.atleta.Atleta;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="equipe")
public class Equipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String local;

    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL)
    private List<Atleta> atletas;

    public Equipe() {
    }

    public Equipe(String nome, String local) {
        this.nome = nome;
        this.local = local;
    }

    public Equipe(Long id, String nome, String local) {
        this.id = id;
        this.nome = nome;
        this.local = local;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public List<Atleta> getAtletas() {
        return atletas;
    }

    public void setAtletas(List<Atleta> atletas) {
        this.atletas = atletas;
    }

    @Override
    public String toString() {
        return "Equipe [id=" + id + ", nome=" + nome + ", local=" + local + ", atletas=" + atletas + "]";
    }
    
}
