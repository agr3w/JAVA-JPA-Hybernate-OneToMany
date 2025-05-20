package entity;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "TB_Funcionario")
public class Funcionario implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_funcionario")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_departamento") // Nossa FK do banco
    private Departamento departamento;

    @Column(name = "nome", length = 100, nullable = false)
    private String nome;

    public Funcionario() {}

    public Funcionario(Long id, String nome) {
        super();
        this.id = id;
        this.nome = nome;
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

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    // Sobrescrevendo o toString() para uma saída mais legível
    @Override
    public String toString() {
        return String.format("Funcionario[id=%d, nome='%s']", id, nome);
    }
}
