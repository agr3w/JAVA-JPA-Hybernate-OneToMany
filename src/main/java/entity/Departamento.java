package entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_Departamento")
public class Departamento implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_departamento")
    private Long id;

    @OneToMany(mappedBy = "departamento")
    private List<Funcionario> funcionarios = new ArrayList<>(); //lista de funcionarios no departamento

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    public Departamento() {}

    public Departamento(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
    }

    // Getters e Setters

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

    public List<Funcionario> getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(List<Funcionario> funcionarios) {
        this.funcionarios = funcionarios;
    }

    public String imprimir() {
        return String.format("[#%d] Nome: %s", this.id, this.nome);
    }
}
